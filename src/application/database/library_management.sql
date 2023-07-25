-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 15, 2023 at 01:00 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library management`
--

-- --------------------------------------------------------

--
-- Table structure for table `admininfo`
--

CREATE TABLE `admininfo` (
  `adminID` int(100) NOT NULL,
  `adminEmail` varchar(255) NOT NULL,
  `adminPassword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admininfo`
--

INSERT INTO `admininfo` (`adminID`, `adminEmail`, `adminPassword`) VALUES
(4, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `bookinfo`
--

CREATE TABLE `bookinfo` (
  `bookTitle` varchar(255) NOT NULL,
  `bookISBN` varchar(255) NOT NULL,
  `bookAuthor` varchar(255) NOT NULL,
  `bookPublisher` varchar(255) NOT NULL,
  `NumCopies` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookinfo`
--

INSERT INTO `bookinfo` (`bookTitle`, `bookISBN`, `bookAuthor`, `bookPublisher`, `NumCopies`) VALUES
('Java : How to Program', '01-2345-6789', 'Deitel, Neil', 'Java', 5),
('Head First Java', '02-3456-7890', 'Sierra, Patrick', 'Oracle', 3),
('Pattern Recognition', '03-4567-8901', 'Herbert, Stany', 'Osbome', 4),
('JLegacy-AI', '04-5678-9012', 'JLegacy-AI', 'JLegacy-AI', 145),
('GRE Verbal Grail', '05-6789-0123', 'Aristotle', 'ETS', 7),
('Introduction to Algorithms ', '06-7890-1234', 'Thomas H. Cormen  ', 'Yale', 11),
('Deep learning with Python ', '07-8901-2345', 'Bengio, Courville ', 'Caltech', 8);

-- --------------------------------------------------------

--
-- Table structure for table `issuedbooks`
--

CREATE TABLE `issuedbooks` (
  `bookTitle` varchar(255) NOT NULL,
  `bookISBN` varchar(255) NOT NULL,
  `bookAuthor` varchar(255) NOT NULL,
  `bookPublisher` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `issuedbooks`
--

INSERT INTO `issuedbooks` (`bookTitle`, `bookISBN`, `bookAuthor`, `bookPublisher`) VALUES
('Pattern Recognition', '03-4567-8901', 'Herbert, Stany', 'Osbome'),
('Introduction to Algorithms ', '06-7890-1234', 'Thomas H. Cormen  ', 'Yale');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE `userinfo` (
  `userID` int(100) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `userEmail` varchar(255) NOT NULL,
  `userPassword` varchar(255) NOT NULL,
  `confirmPass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userinfo`
--

INSERT INTO `userinfo` (`userID`, `Name`, `userEmail`, `userPassword`, `confirmPass`) VALUES
(124, 'User', 'user', 'user', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admininfo`
--
ALTER TABLE `admininfo`
  ADD PRIMARY KEY (`adminID`);

--
-- Indexes for table `bookinfo`
--
ALTER TABLE `bookinfo`
  ADD PRIMARY KEY (`bookISBN`);

--
-- Indexes for table `issuedbooks`
--
ALTER TABLE `issuedbooks`
  ADD PRIMARY KEY (`bookISBN`);

--
-- Indexes for table `userinfo`
--
ALTER TABLE `userinfo`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admininfo`
--
ALTER TABLE `admininfo`
  MODIFY `adminID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `userinfo`
--
ALTER TABLE `userinfo`
  MODIFY `userID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
