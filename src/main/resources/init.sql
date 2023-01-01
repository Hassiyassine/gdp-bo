
INSERT INTO `authority` (`id`, `role_code`, `name_authority`, `role_description`) VALUES
(1, 'GRP1_MEMBRE', 'Membre', 'ce permission et pour voir la liste des membre et aussi pour ajouter et modifier un membre'),
(2, 'GRP2_REFERENTIEL', 'Referentiel', 'ce permission et pour voir la liste des referentiel et aussi pour ajouter et modifier un referentiel'),
(3, 'GRP3_PRODUIT', 'Produit', 'ce permission et pour voir la liste des produit et aussi pour ajouter et modifier un produit'),
(4, 'GRP4_PRODUIT_MEMBRE', 'Produit Membre', 'ce permission et pour voir la liste des produit et aussi pour ajouter et modifier un produit'),
(5, 'GRP5_PERIOD', 'Période', 'ce permission et pour voir la liste des période et aussi pour ajouter et modifier un période'),
(6, 'GRP5_COLLECTE', 'Collecte', 'ce permission et pour voir la liste des collecte et aussi pour ajouter et modifier un collecte');


INSERT INTO `college` (`id`, `description`,`libelle`) VALUES
(1, 'College1\n', 'College1'),
(2, 'College2\n', 'College2');

INSERT INTO `distributeur_categorie` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, NULL, NULL, NULL, NULL, 'Continuous Univariate Distributions et une distributeur de categorie', 'Continuous Univariate Distributions.'),
(2, NULL, NULL, NULL, NULL, 'Multivariate Distributions.', 'Multivariate Distributions.'),
(3, NULL, NULL, NULL, NULL, 'Si vous détenez plus de 50% du capital, vous êtes gérant majoritaire. ...', 'Gérant majoritaire'),
(4, NULL, NULL, NULL, NULL, 'Si vous détenez moins de 50% du capital, vous êtes gérant minoritaire. ...', 'Gérant minoritaire');

INSERT INTO `domaine` (`id`, `description`, `libelle`) VALUES
(1, 'Accès Logement', 'Accès Logement'),
(2, 'Accès Emploi', 'Accès Emploi'),
(3, 'Ecologie', 'Ecologie'),
(4, 'PED', 'PED'),
(5, 'Autre', 'Autre');

INSERT INTO `fonds` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, NULL, '2021-10-04 00:00:00', NULL, NULL, 'Ordinaire', 'Ordinaire'),
(2, NULL, NULL, NULL, NULL, 'Nourricier', 'Nourricier'),
(3, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Maitre', 'Maitre'),
(4, '2021-10-04 00:00:00', NULL, NULL, NULL, 'OPC d\'OPC', 'OPC d\'OPC'),
(5, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Fonds à compartiments', 'Fonds à compartiments'),
(6, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Autres', 'Autres'),
(7, NULL, '2021-10-04 00:00:00', NULL, NULL, 'Ordinaire', 'Ordinaire'),
(8, NULL, NULL, NULL, NULL, 'Nourricier', 'Nourricier'),
(9, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Maitre', 'Maitre'),
(10, '2021-10-04 00:00:00', NULL, NULL, NULL, 'OPC d\'OPC', 'OPC d\'OPC'),
(11, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Fonds à compartiments', 'Fonds à compartiments'),
(12, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Autres', 'Autres');



INSERT INTO `forme_juridique` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, NULL, NULL, NULL, NULL, 'Association\n', 'Association'),
(2, NULL, NULL, NULL, NULL, 'Ecumenical Development Cooperative Society U.A. (Coopérative)\n', 'Ecumenical Development'),
(3, NULL, NULL, NULL, NULL, 'FCPE\n', 'FCPE'),
(4, '2021-10-06', NULL, NULL, NULL, 'FIP\n', 'FIP'),
(5, '2021-10-06', NULL, NULL, NULL, 'Fondation reconnue d\'utilité publique\n', 'Fondation reconnue '),
(6, '2021-10-06', NULL, NULL, NULL, 'indivision volontaire\n', 'indivision volontaire'),
(7, '2021-10-06', NULL, NULL, NULL, 'Livret B\n', 'Livret B'),
(8, '2021-10-06', NULL, NULL, NULL, 'part sociale\n', 'part sociale'),
(9, '2021-10-06', NULL, NULL, NULL, 'PRICAF PRIVEE (véhicule belge spécialisé pour le private equity)\n', 'PRICAF PRIVEE '),
(10, '2021-10-06', NULL, NULL, NULL, 'SA\n', 'SA'),
(11, '2021-10-06', NULL, NULL, NULL, 'SA à capital variable\n', 'SA à capital variable'),
(12, '2021-10-06', NULL, NULL, NULL, 'SA coopérative à capital variable\n', 'SA coopérative à capital variable'),
(13, '2021-10-06', NULL, NULL, NULL, 'SA Coopérative Union d\'Economie Sociale à capital variable\n', 'SA Coopérative Union d\'Economie Sociale à capital variable'),
(14, '2021-10-06', NULL, NULL, NULL, 'SAS\n', 'SAS'),
(15, '2021-10-06', NULL, NULL, NULL, 'SAS à capital variable\n', 'SAS à capital variable'),
(16, '2021-10-06', NULL, NULL, NULL, 'SCA\n', 'SCA'),
(17, '2021-10-06', NULL, NULL, NULL, 'SCA (Société en Commandite par Action) controlée par la Fondation\n', 'SCA '),
(18, '2021-10-06', NULL, NULL, NULL, 'SCA à capital variable\n', 'SCA à capital variable'),
(19, '2021-10-06', NULL, NULL, NULL, 'SCIC\n', 'SCIC'),
(20, '2021-10-06', NULL, NULL, NULL, 'SCIC SA\n', 'SCIC SA'),
(21, '2021-10-06', NULL, NULL, NULL, 'SCIC SA à capital variable\n', 'SCIC SA à capital variable'),
(22, '2021-10-06', NULL, NULL, NULL, 'SCIC SARL\n', 'SCIC SARL'),
(23, '2021-10-06', NULL, NULL, NULL, 'SCIC SARL à capital variable\n', 'SCIC SARL à capital variable'),
(24, '2021-10-06', NULL, NULL, NULL, 'SCIC-SA à capital variable\n', 'SCIC-SA à capital variable'),
(25, '2021-10-06', NULL, NULL, NULL, 'SCR à capital variable\n', 'SCR à capital variable'),
(26, '2021-10-06', NULL, NULL, NULL, 'SICAV\n', 'SICAV'),
(27, '2021-10-06', NULL, NULL, NULL, 'Société civile à capital variable\n', 'Société civile à capital variable'),
(28, '2021-10-06', NULL, NULL, NULL, 'SPV (« fonds commun de créances ») luxembourgeois\n', 'SPV ');


INSERT INTO `gerant_categorie` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, NULL, NULL, NULL, NULL, 'Si vous détenez moins de 50% du capital, vous êtes gérant minoritaire. ...', 'Gérant minoritaire'),
(2, NULL, NULL, NULL, NULL, 'Si vous détenez plus de 50% du capital, vous êtes gérant majoritaire. ...', 'Gérant majoritaire');


INSERT INTO `label` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, NULL, NULL, NULL, NULL, 'Produit de qualité et un produit tres avance', 'Produit de qualité');


INSERT INTO `nature_juridique` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, NULL, '2021-10-04 00:00:00', NULL, NULL, 'FCP', 'FCP'),
(7, '2021-10-04 00:00:00', NULL, NULL, NULL, 'LDD', 'LDD'),
(8, '2021-10-04 00:00:00', NULL, NULL, NULL, 'LIVRET JEUNE', 'LIVRET JEUNE'),
(9, '2021-10-04 00:00:00', NULL, NULL, NULL, 'CAT', 'CAT'),
(10, '2021-10-04 00:00:00', NULL, NULL, NULL, 'BON DE CAISSE', 'BON DE CAISSE'),
(2, NULL, NULL, NULL, NULL, 'SICAV', 'SICAV'),
(3, '2021-10-04 00:00:00', NULL, NULL, NULL, 'FIP', 'FIP'),
(4, '2021-10-04 00:00:00', NULL, NULL, NULL, 'FCPES', 'FCPES'),
(5, '2021-10-04 00:00:00', NULL, NULL, NULL, 'LIVRET A', 'LIVRET A'),
(6, '2021-10-04 00:00:00', NULL, NULL, NULL, 'LIVRET B', 'LIVRET B');


INSERT INTO `role` (`id`, `code`, `description`, `libelle`, `created_at`, `updated_at`, `created_by`, `updated_by`) VALUES
(1, 'ROLE_ADMIN', 'ce role et pour les admin des aplication . pour av', 'Role admin', NULL, NULL, NULL, NULL),
(2, 'ROLE_MEMBRE', 'ce role pour les membre fidel', 'Role membre', NULL, NULL, NULL, NULL);


INSERT INTO `role_authoritys` (`roles_id`, `authoritys_id`) VALUES
(1, 1),
(1, 2),
(1, 3);



INSERT INTO `type_agrement` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, NULL, NULL, NULL, NULL, 'ESUS', 'ESUS'),
(2, NULL, NULL, NULL, NULL, 'Assimilé', 'Assimilé'),
(3, NULL, NULL, NULL, NULL, 'Plein droit\n', 'Plein droit');



INSERT INTO `type_canal` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, '2021-09-25 00:00:00', '2021-09-25 00:00:00', NULL, NULL, 'Etablissement Fianncier', 'Etablissement Fianncier'),
(2, '2021-09-25 00:00:00', '2021-10-04 00:00:00', NULL, NULL, 'Etablissement Solidaire', 'Etablissement Solidaire');


INSERT INTO `type_collecte` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, '2021-09-25 00:00:00', NULL, NULL, NULL, 'Collecte des biodéchets.', 'Collecte des biodéchets.');



INSERT INTO `type_document` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, '2021-09-15 23:55:03', '2021-09-15 23:55:03', NULL, NULL, 'Facture', 'Facture'),
(2, '2021-09-15 23:55:03', '2021-09-15 23:55:03', NULL, NULL, 'Complement', 'Complement'),
(3, '2021-09-15 23:55:03', '2021-09-15 23:55:03', NULL, NULL, 'Autre', 'Atrue');


INSERT INTO `type_financement` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, '2021-09-26 00:00:00', NULL, NULL, NULL, 'Le capital Investissement est une activité financière consistant ', 'CAPITAL INVESTISSEMENT');


INSERT INTO `type_mecanisme` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, '2021-09-26 00:00:00', '2021-10-04 00:00:00', NULL, NULL, 'Financement solidaire', 'Financement solidaire'),
(2, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Partage', 'Partage'),
(3, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Mixte', 'Mixte');



INSERT INTO `type_membe` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(3, NULL, NULL, NULL, NULL, 'membre public et un membre de la realisations ....', 'Membre Public'),
(2, NULL, NULL, NULL, NULL, 'membre public et un membre de la realisations ....', 'Membre Agence'),
(1, '2021-09-14 10:45:58', '2021-09-14 10:45:58', NULL, NULL, 'Fidél', 'Membre Prive');


INSERT INTO `type_model` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, '2021-09-26 00:00:00', '2021-10-04 00:00:00', NULL, NULL, 'Bancaire', 'Bancaire'),
(2, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Bancaire Perso', 'Bancaire Perso'),
(3, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Assurance', 'Assurance'),
(4, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Financeur Solidaire', 'Financeur Solidaire'),
(5, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Partage', 'Partage'),
(6, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Hybride', 'Hybride'),
(7, '2021-10-04 00:00:00', NULL, NULL, NULL, 'EntSolidaire', 'EntSolidaire'),
(8, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Circuit Court', 'Circuit Court');



INSERT INTO `type_produit` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(2, NULL, NULL, NULL, NULL, 'Logistique et transport et un type de  produit', 'Logistique et transport'),
(3, NULL, '2021-09-21 00:00:00', NULL, NULL, 'type de Electronique', 'Electronique'),
(4, NULL, '2021-09-21 00:00:00', NULL, NULL, 'immobilier type produit', 'immobilier');



INSERT INTO `type_support` (`id`, `created_at`, `updated_at`, `created_by`, `updated_by`, `description`, `libelle`) VALUES
(1, NULL, NULL, NULL, NULL, 'Actions non cotées', 'Actions non cotées'),
(2, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Bon de caisse', 'Bon de caisse'),
(3, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Compte courant associés', 'Compte courant associés'),
(4, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Dépôt à terme', 'Dépôt à terme'),
(5, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Indivision', 'Indivision'),
(6, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Livret', 'Livret'),
(7, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Micro-prêt', 'Micro-prêt'),
(8, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Obligations', 'Obligations'),
(9, '2021-10-04 00:00:00', NULL, NULL, NULL, 'OPC', 'OPC'),
(10, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Option de partage sur Livret', 'Option de partage sur Livret'),
(11, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Part sociale', 'Part sociale'),
(12, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Portefeuille électronique', 'Portefeuille électronique'),
(13, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Titre associatif', 'Titre associatif'),
(14, '2021-10-04 00:00:00', NULL, NULL, NULL, 'Titre participatif', 'Titre participatif');



INSERT INTO `user` (`id`, `created_on`, `enabled`, `first_name`, `last_name`, `user_key`, `updated_on`, `user_name`, `role_id`, `email`, `membre_id`, `address_line1`, `address_line2`, `personal_phone`, `postal_code`, `professtional_phone`, `first_login`, `avatar`, `description`, `fonction`, `service`) VALUES
(1, '2021-09-08 15:16:29', b'1', 'yassine', 'Hassi', '$2a$10$12igiYAAusBiAXXzQTIGkuYNcszrUlw8OOrrEpEB0kSfp3bL0p48i', '2021-10-08 21:10:12', 'admin', 1, 'yassine.ismail.hassi@gmail.com', null, 'rue elhachemi elmestari ', NULL, NULL, NULL, NULL, b'1', null, NULL, 'ingénieur', 'Informatique');

