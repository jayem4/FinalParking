-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 07, 2025 at 10:23 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `intercafe`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_forgotpass`
--

CREATE TABLE `tbl_forgotpass` (
  `fp_id` int(20) NOT NULL,
  `c_id` int(20) NOT NULL,
  `fp_question` varchar(255) NOT NULL,
  `fp_answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_forgotpass`
--

INSERT INTO `tbl_forgotpass` (`fp_id`, `c_id`, `fp_question`, `fp_answer`) VALUES
(1, 102, 'What was your mother\'s maiden name?', 'XgcEEXO4IRNd5inWVoCLhCC9ln5VzX605TXzr9qnve0='),
(2, 103, 'What was your mother\'s maiden name?', '8fs6nbAtzNJJmQOS+T8ML55RMLyyH59QOgvILye9jbw='),
(3, 104, 'What city were you born in?', 'RlkxwXrz5rzxwIVemQT92UgeWa10DQTXyP2ygDs1YUc='),
(20, 122, 'What was your mother\'s maiden name?', 'QqQkNmeGSFXLWCdl09fHA+BT0fFmfTjraW+k3kPSQfk='),
(21, 123, 'What is your favorite pet\'s name?', 'ruQjWztEhRd0CyRScwNXE876T3rqjJ+UshsTWAwF/74='),
(22, 124, 'What is your favorite pet\'s name?', 'BOd7+Plcs+GjalnR6ThXxBGTDbZGtGwhigNS5DICPPI='),
(23, 125, 'What is your favorite pet\'s name?', '8nVVrxXZ5dafZ5yT0/1GoPlLfkmdSBHLQK9pioKLktE=');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_log`
--

CREATE TABLE `tbl_log` (
  `log_id` int(20) NOT NULL,
  `c_id` int(25) NOT NULL,
  `log_event` varchar(255) NOT NULL,
  `log_description` varchar(255) NOT NULL,
  `log_timestamp` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_log`
--

INSERT INTO `tbl_log` (`log_id`, `c_id`, `log_event`, `log_description`, `log_timestamp`) VALUES
(197, 104, 'LOGIN', 'Logged in successfully', '2025-04-02 15:14:48'),
(199, 104, 'LOGIN', 'Logged in successfully', '2025-04-02 15:18:01'),
(200, 104, 'LOGIN', 'Logged in successfully', '2025-04-02 15:24:58'),
(277, 125, 'User Added', 'User \'Jeja Shakshak\' with username \'jejaa\' was added.', '2025-04-07 16:07:22'),
(278, 125, 'LOGIN', 'Logged in successfully', '2025-04-07 16:08:11'),
(279, 125, 'LOGOUT', 'Logged out successfully', '2025-04-07 16:08:37'),
(280, 125, 'LOGIN', 'Logged in successfully', '2025-04-07 16:09:11'),
(281, 125, 'LOGOUT', 'Logged out successfully', '2025-04-07 16:09:26'),
(282, 124, 'LOGIN', 'Logged in successfully', '2025-04-07 16:09:29'),
(283, 124, 'LOGOUT', 'Logged out successfully', '2025-04-07 16:09:57'),
(284, 124, 'LOGIN', 'Logged in successfully', '2025-04-07 16:10:05'),
(285, 124, 'LOGOUT', 'Logged out successfully', '2025-04-07 16:10:11'),
(286, 124, 'LOGIN', 'Logged in successfully', '2025-04-07 16:10:16'),
(287, 103, 'LOGIN', 'Logged in successfully', '2025-04-07 16:17:45'),
(288, 103, 'LOGIN', 'Logged in successfully', '2025-04-07 16:18:18'),
(289, 103, 'LOGOUT', 'Logged out successfully', '2025-04-07 16:18:44'),
(290, 103, 'LOGIN', 'Logged in successfully', '2025-04-07 16:18:50'),
(291, 103, 'LOGIN', 'Logged in successfully', '2025-04-07 16:20:31'),
(292, 103, 'LOGOUT', 'Logged out successfully', '2025-04-07 16:21:01'),
(293, 103, 'LOGIN', 'Logged in successfully', '2025-04-07 16:21:21');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_member`
--

CREATE TABLE `tbl_member` (
  `m_id` int(20) NOT NULL,
  `m_fname` varchar(255) NOT NULL,
  `m_lname` varchar(255) NOT NULL,
  `m_email` varchar(255) NOT NULL,
  `m_contact` varchar(255) DEFAULT NULL,
  `m_status` varchar(255) DEFAULT NULL,
  `m_username` varchar(50) NOT NULL,
  `m_type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `c_id` int(20) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(150) NOT NULL,
  `contactnum` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL,
  `u_image` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`c_id`, `fname`, `lname`, `email`, `username`, `password`, `contactnum`, `type`, `status`, `u_image`) VALUES
(28, 'Aaron', 'Ruiz', 'aaronruizz@yahoo.com', 'aaron', 'hmfD7Go+m8JbK4RhyJYsj3iEWMyyRnZEmohKf5t6lYA=', '09182327015', 'Admin', 'Active', ''),
(46, 'jerics', 'alcala', 'jericho@gmail.com', 'ahh', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', '123123123', 'Member', 'Active', ''),
(47, 'felanie', 'alcala', 'felaniee@gmail.com', 'feaa', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', '09211818', 'Admin', 'Active', ''),
(51, 'eko', 'gwapo', 'ekogwapoo@gmail.com', 'ekooo', 'hmfD7Go+m8JbK4RhyJYsj3iEWMyyRnZEmohKf5t6lYA=', '123123123', 'Admin', 'Active', ''),
(92, 'james', 'andrew', 'james@gmail.com', 'james', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', '09123123', 'Member', 'Active', ''),
(102, 'waawa', 'aaa', 'wadew@gmail.com', 'dadadada', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', '3123123123', 'Member', 'Active', ''),
(103, 'Jinny', 'Parages', 'jinnyyparagess@gmail.com', 'jinnyy', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', '0923232823', 'Member', 'Active', 'src/userimage/jinyy.jpg'),
(104, 'Maylord', 'Rico', 'maylordrico321@gmail.com', 'eloynes', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', '09077955120', 'Admin', 'Active', ''),
(122, 'gw', 'gwergwe', 'gwer@gmail.com', 'gwgwrwe', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', '123123123123', 'Member', 'Pending', ''),
(123, 'dafaadad', 'adad', 'adadd@gmail.com', 'adadadad', 'ky88G1YlfOhTmsJp16q0JVDaz4gY0HXwvfGZBWKq4+8=', '12312312312', 'Member', 'Pending', ''),
(124, 'elsa', 'anna', 'qewqe@gmail.com', 'elsaa', 'hmfD7Go+m8JbK4RhyJYsj3iEWMyyRnZEmohKf5t6lYA=', '3123123123123', 'Member', 'Active', ''),
(125, 'Jeja', 'Shakshak', 'jejashakshak@gmail.com', 'jeja', 'HIEjjw8P4EO6telQ/c244M1PEMW8gH23IIC7SYqBCU8=', '09182326915', 'Member', 'Active', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_forgotpass`
--
ALTER TABLE `tbl_forgotpass`
  ADD PRIMARY KEY (`fp_id`),
  ADD KEY `c_id` (`c_id`);

--
-- Indexes for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `c_id` (`c_id`);

--
-- Indexes for table `tbl_member`
--
ALTER TABLE `tbl_member`
  ADD PRIMARY KEY (`m_id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`c_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_forgotpass`
--
ALTER TABLE `tbl_forgotpass`
  MODIFY `fp_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `tbl_log`
--
ALTER TABLE `tbl_log`
  MODIFY `log_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=294;

--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `m_id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `c_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=126;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_forgotpass`
--
ALTER TABLE `tbl_forgotpass`
  ADD CONSTRAINT `tbl_forgotpass_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `tbl_user` (`c_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tbl_log`
--
ALTER TABLE `tbl_log`
  ADD CONSTRAINT `tbl_log_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `tbl_user` (`c_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
