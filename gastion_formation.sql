-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 18 jan. 2021 à 14:30
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP :  7.2.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gastion_formation`
--

-- --------------------------------------------------------

--
-- Structure de la table `employers`
--

CREATE TABLE `employers` (
  `id` int(11) NOT NULL,
  `matricule` varchar(10) NOT NULL,
  `nom` varchar(15) NOT NULL,
  `prenom` varchar(15) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `ville` varchar(15) NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `employers`
--

INSERT INTO `employers` (`id`, `matricule`, `nom`, `prenom`, `username`, `password`, `ville`, `admin`) VALUES
(1, 'EM234', 'Bettaoui', 'Mohammed', 'Midoo', 'Amiin90', 'safi', 1),
(2, 'EM457', 'Souita', 'Hamza', 'Bartolomio', 'azerty', 'Agadir', 0),
(3, 'EM24.0', 'Bettaoui', 'Houssame', 'Eddine', 'azerty123', 'Salé', 0),
(4, 'EM5', 'Aidi', 'abdilllah', 'Adsence', 'azerty12345', 'Rabat', 0);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `code` varchar(6) NOT NULL,
  `libelle` varchar(15) NOT NULL,
  `description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `formation`
--

INSERT INTO `formation` (`code`, `libelle`, `description`) VALUES
('Form11', 'C#', 'POO......Desktop.......Web'),
('Form12', 'Node.js', 'Building Rest API.....working with Asycronos funct'),
('Form13', 'Python', 'Introduction to Data Science');

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

CREATE TABLE `session` (
  `code` varchar(6) NOT NULL,
  `libelle` varchar(15) NOT NULL,
  `formation` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`code`, `libelle`, `formation`) VALUES
('Ses10', 'juin-juillet', 'Form12'),
('Ses11', 'novembre-aout', 'Form11'),
('Ses12', 'octobre-mars', 'Form13');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `employers`
--
ALTER TABLE `employers`
  ADD PRIMARY KEY (`id`,`matricule`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`code`);

--
-- Index pour la table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`code`),
  ADD KEY `fk_foreign_codeformation` (`formation`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `employers`
--
ALTER TABLE `employers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `fk_foreign_codeformation` FOREIGN KEY (`formation`) REFERENCES `formation` (`code`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
