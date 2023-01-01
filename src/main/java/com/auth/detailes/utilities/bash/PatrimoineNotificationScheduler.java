package com.auth.detailes.utilities.bash;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.business.entites.patrimoine.Notification;
import com.auth.detailes.business.entites.patrimoine.Patrimoine;
import com.auth.detailes.service.IUtilisateurService;
import com.auth.detailes.service.mailer.ApplicationMailerTemplate;
import com.auth.detailes.service.mpl.NotificationService;
import com.auth.detailes.service.mpl.PatrimoineService;
import com.auth.detailes.service.referentiel.DocumentService;
import com.auth.detailes.utilities.enums.Status;
import com.auth.detailes.web.requests.PatrimoineSearchRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;


@Configuration
@EnableAutoConfiguration
@EnableScheduling
@EnableAsync
@EnableAspectJAutoProxy
@Slf4j @AllArgsConstructor
public class PatrimoineNotificationScheduler {


	private final PatrimoineService service;
	private final NotificationService notificationService;
	private final IUtilisateurService utilisateurService;
	private ApplicationMailerTemplate mailerTemplate;
	private DocumentService documentService;

    @Scheduled(cron = "0 * * * * *", zone = "Africa/Casablanca")
    public void schedulePatrimoine() {
		PatrimoineSearchRequest searchRequest = new PatrimoineSearchRequest();
		//searchRequest.setIsNotifiedContract(Boolean.FALSE);
		//searchRequest.setIsNotifiedParent(Boolean.FALSE);
		if(this.service.count()>0){
	        Page<Patrimoine> page = this.service.findAll(searchRequest, PageRequest.of(0,Math.toIntExact((this.service.count()))));
	        if(!page.getContent().isEmpty()){
				page.getContent().forEach(e->{
					if(haveDateExpired(e) && e.getStatus().equals(Status.ENREGISTRER)){
					Notification notification;
					Optional<Notification> optional = this.notificationService.findByPatrimoineId(e.getId());
						notification = optional.orElseGet(Notification::new);
						boolean dateDocumentExpiration = false;
						boolean dateHomologationExpiration = false;
						boolean dateContractExpiration = false;
						if(this.isDateHomologationExpiration(e)){
							notification.setDateHomologationExpiration(e.getDateHomologationExpiration());
							notification.setDateHomologationExpirationSend(Boolean.TRUE);
							e.setIsNotified(Boolean.TRUE);
							dateHomologationExpiration=true;
						}
						if(this.isDateContractExpiration(e) && e.getContract() !=null && !e.getContract().getIsNotified()){
							notification.setDateDeliverance(e.getContract().getDateEnd());
							notification.setDateDeliveranceSend(Boolean.TRUE);
							e.getContract().setIsNotified(Boolean.TRUE);
							dateContractExpiration=true;
						}
						if(this.isDateDocumentExpiration(e) && e.getDocuments() !=null){
						notification = this.handelDocument(e,notification);
						dateDocumentExpiration=true;
						}
						notification.setPatrimoine(e);
						if (e.getCreatedBy()!=null &&
								dateDocumentExpiration ||
								dateContractExpiration &&
									!e.getContract().getIsNotified()
								|| dateHomologationExpiration){
							Optional<User>  optionalUser = utilisateurService.find(e.getCreatedBy());
							if(optionalUser.isPresent()){
								User current = optionalUser.get();
								this.notificationService.save(notification);
								try {
									mailerTemplate.notifyPatrimone(String.format("%s",current.getUsername()),current.getEmail(),String.format("Notification PATRIMOINE N° %s BIENTÔT EXPIRE ID° %s",e.getName(),e.getId()));
								}catch (Exception ex){
									log.error("cant to send email{}",ex.getMessage());
								}
							}
						}
						this.service.save(e);
					}
				});
			}
		}
	}

	private boolean isDateDocumentExpiration(Patrimoine e) {
		AtomicBoolean isReadyToNotified = new AtomicBoolean(false);
		if(e.getDocuments() != null){
			e.getDocuments().forEach(element->{
  				if(this.calculateInputDayToCurrentDate(element.getDateDel(), 3) <= 3 && !element.getIsNotified() && Stream.of("1","2","3","4").anyMatch(el->el.equals(element.getTypeDocument().getUid()))){
					isReadyToNotified.set(true);
				}
			});
		}
		return isReadyToNotified.get();
	}

	private boolean isDateContractExpiration(Patrimoine entity){
		if(entity.getContract()!=null && entity.getContract().getDateEnd()!=null){
			if(this.calculateInputDayToCurrentDate(entity.getContract().getDateEnd(),3)<=3){
				System.err.println(this.calculateInputDayToCurrentDate(entity.getContract().getDateEnd(),3));
				return true;
			}
		}
		return false;
	}

	private boolean isDateHomologationExpiration(Patrimoine entity){
		if(entity.getDateHomologationExpiration()!=null){
			if(this.calculateInputDayToCurrentDate(entity.getDateHomologationExpiration(),3)<=3 && !entity.getIsNotified()){
				return true;
			}
		}
		return false;
	}


	private boolean haveDateExpired(Patrimoine entity){
		if(isDateContractExpiration(entity)
				|| isDateHomologationExpiration(entity)
				|| this.isDateDocumentExpiration(entity)){
				return true;
		}
		return false;
	}

	
	int calculateInputDayToCurrentDate(Date startDate, int nbrDayForCalc) {
		Calendar dateNow = Calendar.getInstance();
        Calendar dateDelai = Calendar.getInstance();
        dateDelai.setTime(startDate);
        dateDelai.add(Calendar.DATE, nbrDayForCalc);

        long start = dateDelai.getTimeInMillis();
        long end = dateNow.getTimeInMillis();
        return (int) TimeUnit.MILLISECONDS.toDays(start - end);
	}

	private Notification handelDocument(Patrimoine e, Notification notification) {
		e.getDocuments().forEach(element->{
			// check type document i-f have uid 1
			if(element.getTypeDocument().getUid().equals("1") && !element.getIsNotified()){
				notification.setNoteRenseignement(element.getDateDel());
				notification.setNoteRenseignementSend(Boolean.TRUE);
				element.setIsNotified(Boolean.TRUE);
				this.documentService.update(element.id,element);
			}
			// end check.
			// check type document i-f have uid 2
			if(element.getTypeDocument().getUid().equals("2") && !element.getIsNotified()){
				notification.setPlanCadastrale(element.getDateDel());
				notification.setPlanCadastraleSend(Boolean.TRUE);
				element.setIsNotified(Boolean.TRUE);
				this.documentService.update(element.id,element);
			}
			// end check.
			// check type document i-f have uid 3
			if(element.getTypeDocument().getUid().equals("3") && !element.getIsNotified()){
				notification.setPlanArchitecte(element.getDateDel());
				notification.setPlanArchitecteSend(Boolean.TRUE);
				element.setIsNotified(Boolean.TRUE);
				this.documentService.update(element.id,element);
			}
			// end check.
			// check type document i-f have uid 4
			if(element.getTypeDocument().getUid().equals("4") && !element.getIsNotified()){
				notification.setCompromisPropriete(element.getDateDel());
				notification.setCompromisProprieteSend(Boolean.TRUE);
				element.setIsNotified(Boolean.TRUE);
				this.documentService.update(element.id,element);
			}
			// end check.
		});
		return notification;
	}
}
