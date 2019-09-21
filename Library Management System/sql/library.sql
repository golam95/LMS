-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2019 at 05:59 AM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `booklist`
--

CREATE TABLE `booklist` (
  `id` int(11) NOT NULL,
  `typebook` varchar(100) NOT NULL,
  `bookname` varchar(100) NOT NULL,
  `bookid` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booklist`
--

INSERT INTO `booklist` (`id`, `typebook`, `bookname`, `bookid`) VALUES
(1, 'Story Book', 'The Cat in the Hat', 'S0013'),
(2, 'Story Book', 'Charlotte’s Web', 'S0014'),
(3, 'Story Book', 'Harold and the Purple', 'S0015'),
(4, 'Story Book', 'Little Women by Louis', 'S0016'),
(5, 'Cook Book', 'A Homemade Life', 'C0011'),
(6, 'Cook Book', 'A Man, A Cave', 'C0012'),
(7, 'Cook Book', 'A Saving Helping', 'C0013'),
(8, 'Cook Book', 'Against all Grain', 'C0014'),
(9, 'Text Book', 'Calculus', 'T0011'),
(10, 'Text Book', 'Analysis of Genes and Genomes', 'T0012');

-- --------------------------------------------------------

--
-- Table structure for table `bookorder`
--

CREATE TABLE `bookorder` (
  `id` int(11) NOT NULL,
  `bookId` varchar(11) NOT NULL,
  `usercode` varchar(100) NOT NULL,
  `date` varchar(100) NOT NULL,
  `role` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bookorder`
--

INSERT INTO `bookorder` (`id`, `bookId`, `usercode`, `date`, `role`) VALUES
(1, 'C0012', 'Meh6', '07-08-2019', 'Allow'),
(2, 'T0012', 'Meh6', '07-08-2019', 'Allow'),
(3, 'T0012', 'Meh6', '07-08-2019', 'Allow'),
(4, 'S0016', 'Meh6', '07-08-2019', 'Allow'),
(5, 'S0014', 'Nus45', '07-08-2019', 'Allow'),
(6, 'S0016', 'Nus45', '07-08-2019', 'Not Allow'),
(7, 'T0011', 'Rab64', '08-08-2019', 'Not Allow'),
(8, 'T0012', 'Rab64', '08-08-2019', 'Allow'),
(9, 'C0013', 'Nus45', '08-08-2019', 'Not Allow'),
(10, 'C0012', 'Rab64', '08-08-2019', 'Not Allow'),
(11, 'C0013', 'Rab64', '08-08-2019', 'Not Allow'),
(12, 'C0011', 'Rab64', '08-08-2019', 'Not Allow'),
(13, 'C0014', 'Rab64', '08-08-2019', 'Not Allow'),
(14, 'S0015', 'Rab64', '08-08-2019', 'Not Allow'),
(15, 'S0016', 'Rab64', '08-08-2019', 'Not Allow');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` int(11) NOT NULL,
  `username` varchar(200) NOT NULL,
  `useremail` varchar(200) NOT NULL,
  `userroll` varchar(200) NOT NULL,
  `usergenerate` varchar(200) NOT NULL,
  `userdate` varchar(200) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `username`, `useremail`, `userroll`, `usergenerate`, `userdate`, `password`) VALUES
(1, 'Mehedi Hasan', 'mehedi@gmail.com', 'CIS-34', 'Meh6', '07-08-2019', '1234'),
(2, 'Ali', 'ali@gmail.com', 'CIS-36', 'Ali12', '07-08-2019', '1234'),
(3, 'Meekat Sbn', 'meekat@gmail.com', 'admin', 'admin', '07-08-2019', '123456'),
(4, 'Nusrat ', 'nusrat@gmail.com', 'CIS-35', 'Nus45', '07-08-2019', '1234'),
(5, 'Rabbi', 'rabbi@gmail.com', 'CIS-35', 'Rab64', '08-08-2019', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booklist`
--
ALTER TABLE `booklist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bookorder`
--
ALTER TABLE `bookorder`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booklist`
--
ALTER TABLE `booklist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `bookorder`
--
ALTER TABLE `bookorder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
