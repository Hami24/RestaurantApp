-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 25, 2018 at 10:04 AM
-- Server version: 5.7.14
-- PHP Version: 7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student1`
--

-- --------------------------------------------------------

--
-- Table structure for table `jela`
--

CREATE TABLE `jela` (
  `id_jela` int(11) NOT NULL,
  `naziv` varchar(45) NOT NULL,
  `cijena` double NOT NULL,
  `slika` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `jela`
--

INSERT INTO `jela` (`id_jela`, `naziv`, `cijena`, `slika`) VALUES
(17, 'Begova corba', 5, 'begova-corba.jpg'),
(19, 'Raznjici', 10, 'raznjici.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE `korisnici` (
  `korisnici_id` int(11) NOT NULL,
  `korIme` varchar(45) NOT NULL,
  `lozinka` varchar(45) NOT NULL,
  `ime` varchar(45) NOT NULL,
  `prezime` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `adresa` varchar(45) NOT NULL,
  `tip_korisnika` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`korisnici_id`, `korIme`, `lozinka`, `ime`, `prezime`, `email`, `adresa`, `tip_korisnika`) VALUES
(36, 'hami22', 'hami', 'Muahmed', 'Handukic', 'muhamed@gmail.com', '12 Da', 'Admin'),
(50, 'da', 'da', 'da', 'da', 'da@gmail.com', 'da', 'Korisnik');

-- --------------------------------------------------------

--
-- Table structure for table `narudzbe`
--

CREATE TABLE `narudzbe` (
  `id_narudzbe` int(11) NOT NULL,
  `korisnici_id_nar` int(11) NOT NULL,
  `id_jela_nar` int(11) NOT NULL,
  `datum_narudzbe` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `narudzbe`
--

INSERT INTO `narudzbe` (`id_narudzbe`, `korisnici_id_nar`, `id_jela_nar`, `datum_narudzbe`) VALUES
(21, 50, 19, '2016-11-26 12:51:05'),
(22, 50, 17, '2016-11-26 12:51:51');

-- --------------------------------------------------------

--
-- Table structure for table `rezervacije`
--

CREATE TABLE `rezervacije` (
  `id_rezervacije` int(11) NOT NULL,
  `korisnici_id` int(11) NOT NULL,
  `id_stolovi` int(11) NOT NULL,
  `datum` date NOT NULL,
  `vrijeme_pocetka` time NOT NULL,
  `vrijeme_kraj` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rezervacije`
--

INSERT INTO `rezervacije` (`id_rezervacije`, `korisnici_id`, `id_stolovi`, `datum`, `vrijeme_pocetka`, `vrijeme_kraj`) VALUES
(35, 50, 1, '2016-11-26', '14:00:00', '17:00:00'),
(36, 50, 1, '2016-11-25', '15:00:00', '18:00:00'),
(37, 50, 13, '2016-11-26', '14:00:00', '17:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `stolovi`
--

CREATE TABLE `stolovi` (
  `id_stolovi` int(11) NOT NULL,
  `naziv` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `stolovi`
--

INSERT INTO `stolovi` (`id_stolovi`, `naziv`) VALUES
(1, 'Stol za dvoje'),
(13, 'Stol za 4');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jela`
--
ALTER TABLE `jela`
  ADD PRIMARY KEY (`id_jela`);

--
-- Indexes for table `korisnici`
--
ALTER TABLE `korisnici`
  ADD PRIMARY KEY (`korisnici_id`);

--
-- Indexes for table `narudzbe`
--
ALTER TABLE `narudzbe`
  ADD PRIMARY KEY (`id_narudzbe`),
  ADD KEY `id_jela_idx` (`id_jela_nar`),
  ADD KEY `korisnici_id_idx` (`korisnici_id_nar`);

--
-- Indexes for table `rezervacije`
--
ALTER TABLE `rezervacije`
  ADD PRIMARY KEY (`id_rezervacije`),
  ADD KEY `korisnici_id_idx` (`korisnici_id`),
  ADD KEY `id_stolovi_idx` (`id_stolovi`);

--
-- Indexes for table `stolovi`
--
ALTER TABLE `stolovi`
  ADD PRIMARY KEY (`id_stolovi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jela`
--
ALTER TABLE `jela`
  MODIFY `id_jela` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `korisnici`
--
ALTER TABLE `korisnici`
  MODIFY `korisnici_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;
--
-- AUTO_INCREMENT for table `narudzbe`
--
ALTER TABLE `narudzbe`
  MODIFY `id_narudzbe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `rezervacije`
--
ALTER TABLE `rezervacije`
  MODIFY `id_rezervacije` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT for table `stolovi`
--
ALTER TABLE `stolovi`
  MODIFY `id_stolovi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `narudzbe`
--
ALTER TABLE `narudzbe`
  ADD CONSTRAINT `id_jela_nar` FOREIGN KEY (`id_jela_nar`) REFERENCES `jela` (`id_jela`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `korisnici_id_nar` FOREIGN KEY (`korisnici_id_nar`) REFERENCES `korisnici` (`korisnici_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rezervacije`
--
ALTER TABLE `rezervacije`
  ADD CONSTRAINT `id_stolovi_rez` FOREIGN KEY (`id_stolovi`) REFERENCES `stolovi` (`id_stolovi`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `korisnici_id_rez` FOREIGN KEY (`korisnici_id`) REFERENCES `korisnici` (`korisnici_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
