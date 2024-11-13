-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 23, 2024 at 06:40 AM
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
-- Database: `console_chaos`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `currentTime` ()   BEGIN
	SELECT CURRENT_TIMESTAMP;
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getMails` ()   BEGIN
	SELECT mail FROM login_details;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_gaming_logs` (OUT `u1` VARCHAR(50), OUT `u2` VARCHAR(50), OUT `u3` VARCHAR(50), OUT `u4` VARCHAR(50), OUT `u5` VARCHAR(50), OUT `u6` VARCHAR(50))   begin
	SELECT login_details.username INTO u1 FROM login_details INNER JOIN digicricketlogs ON 								login_details.username=digicricketlogs.username;
    SELECT login_details.username INTO u2 FROM login_details INNER JOIN crazycricketlogs ON 			login_details.username=crazycricketlogs.username;
    SELECT login_details.username INTO u3 FROM login_details INNER JOIN tictactoelogs ON login_details.username=tictactoelogs.username;
    SELECT login_details.username INTO u4 FROM login_details INNER JOIN hangmanlogs ON login_details.username=hangmanlogs.username;
    SELECT login_details.username INTO u5 FROM login_details INNER JOIN tickingtimelogs ON login_details.username=tickingtimelogs.username;
    SELECT login_details.username INTO u6 FROM login_details INNER JOIN quizgamelogs ON login_details.username=quizgame.username;
    INSERT INTO gaminglogs VALUES (u1,u2,3,u4,u5,u6);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `update_credit_coins` ()   BEGIN
	UPDATE login_details SET creditPoints = creditPoints + coinsEarned;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `available_games`
--

CREATE TABLE `available_games` (
  `ID` int(11) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `MaxPlayers` int(11) NOT NULL,
  `credit_points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `available_games`
--

INSERT INTO `available_games` (`ID`, `Name`, `MaxPlayers`, `credit_points`) VALUES
(1, 'Digi Cricket', 1, 600),
(2, 'Crazy Cricket', 1, 800),
(3, 'Tic Tac Toe', 2, 500),
(4, 'Hangman', 2, 600),
(5, 'Ticking Time', 1, 300),
(6, 'QuizGame', 1, 800);

-- --------------------------------------------------------

--
-- Table structure for table `captcha_codes`
--

CREATE TABLE `captcha_codes` (
  `ID` int(11) NOT NULL,
  `captcha_code` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `captcha_codes`
--

INSERT INTO `captcha_codes` (`ID`, `captcha_code`) VALUES
(1, '7J9tL4'),
(2, 'B2mF6q'),
(3, 'X8rZ1y'),
(4, 'C3wH7p'),
(5, 'T5kV9x'),
(6, 'J4nR2s'),
(7, 'L7cP3q'),
(8, 'Y1vM8r'),
(9, 'F6xN4k'),
(10, 'P3wL2t'),
(11, 'R8yK5b'),
(12, 'H9sV1d'),
(13, 'D2fW7j'),
(14, 'Q6tP3m'),
(15, 'Z4kN9r'),
(16, 'M7vJ2x'),
(17, 'X1yL5t'),
(18, 'S3rC9b'),
(19, 'T2pH6k'),
(20, 'L9xW1v'),
(21, 'Y4rN7p'),
(22, 'F5mJ2q'),
(23, 'V8tL3r'),
(24, 'J6xK9b'),
(25, 'C1qP7t'),
(26, 'N4vH2k'),
(27, 'R3xL5m'),
(28, 'H8yJ1p'),
(29, 'W2tF9b'),
(30, 'X7mP4q'),
(31, 'K3rN6x'),
(32, 'L1yJ9t'),
(33, 'P4wH7k'),
(34, 'M2xC5r'),
(35, 'Q9vL1t'),
(36, 'T6kN3p'),
(37, 'F7xJ2b'),
(38, 'R1yL4t'),
(39, 'J9pH5k'),
(40, 'N3vC2x'),
(41, 'L7rP8q'),
(42, 'T1xJ4m'),
(43, 'Y6kN9b'),
(44, 'F2wP7t'),
(45, 'X4mL1r'),
(46, 'Q8tJ3x'),
(47, 'H5yK2p'),
(48, 'R7xL9b'),
(49, 'M1pN4t'),
(50, 'P6wJ2r'),
(51, 'C9xL5k'),
(52, 'T3vH1p'),
(53, 'Y2rN8t'),
(54, 'F4mJ9x'),
(55, 'L6tP1b'),
(56, 'X7kN2r'),
(57, 'R5xJ8p'),
(58, 'H9yL3t'),
(59, 'P2wK4m'),
(60, 'Q1rN7x'),
(61, 'T8xL6p'),
(62, 'J3mP1r'),
(63, 'C7vH9k'),
(64, 'Y4tL2b'),
(65, 'N5xJ8p'),
(66, 'R1pK3t'),
(67, 'F6mL9x'),
(68, 'T2yN4r'),
(69, 'X8kJ5p'),
(70, 'P3wL1t'),
(71, 'H9rM2x'),
(72, 'Q4xJ7b'),
(73, 'L1pN6k'),
(74, 'T5vC9r'),
(75, 'Y2xH3p'),
(76, 'F7tL8m'),
(77, 'R9kP1x'),
(78, 'J6rN4t'),
(79, 'C3wL2p'),
(80, 'X7mH5b'),
(81, 'N1pJ9t'),
(82, 'T4rL6k'),
(83, 'Y8xN3p'),
(84, 'F2mJ7r'),
(85, 'P5tL1x'),
(86, 'H3kN9b'),
(87, 'R8xJ2p'),
(88, 'L4wT7m'),
(89, 'X9rC1t'),
(90, 'J2pL6k'),
(91, 'Q7xN3b'),
(92, 'F5tJ9p'),
(93, 'T1mL4r'),
(94, 'Y6kP2x'),
(95, 'R3xL8b'),
(96, 'H7pN1t'),
(97, 'L9vJ2k'),
(98, 'X4tM6r'),
(99, 'P1wL7b'),
(100, 'C8xN3p');

-- --------------------------------------------------------

--
-- Table structure for table `crazycricketlogs`
--

CREATE TABLE `crazycricketlogs` (
  `Username` varchar(50) DEFAULT NULL,
  `TimeOfPlaying` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CreditsUsed` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `crazycricketlogs`
--

INSERT INTO `crazycricketlogs` (`Username`, `TimeOfPlaying`, `CreditsUsed`) VALUES
('Satvik', '2024-08-23 03:47:06', 800);

-- --------------------------------------------------------

--
-- Table structure for table `digicricketlogs`
--

CREATE TABLE `digicricketlogs` (
  `Username` varchar(50) NOT NULL,
  `TimeOfPlaying` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CreditsUsed` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `digicricketlogs`
--

INSERT INTO `digicricketlogs` (`Username`, `TimeOfPlaying`, `CreditsUsed`) VALUES
('Satvik', '2024-08-23 03:45:38', 600);

-- --------------------------------------------------------

--
-- Table structure for table `gaminglogs`
--

CREATE TABLE `gaminglogs` (
  `digicricket` varchar(50) NOT NULL,
  `crazycricket` varchar(50) NOT NULL,
  `tictactoe` varchar(50) NOT NULL,
  `hangman` varchar(50) NOT NULL,
  `tickingtime` varchar(50) NOT NULL,
  `quizgame` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hangmanlogs`
--

CREATE TABLE `hangmanlogs` (
  `Username` varchar(50) DEFAULT NULL,
  `TimeOfPlaying` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CreditsUsed` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hangman_words`
--

CREATE TABLE `hangman_words` (
  `id` int(11) NOT NULL,
  `word` varchar(50) NOT NULL,
  `Hint` longtext NOT NULL,
  `difficulty` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hangman_words`
--

INSERT INTO `hangman_words` (`id`, `word`, `Hint`, `difficulty`) VALUES
(1, 'Apple', 'A fruit that keeps the doctor away', 'easy'),
(2, 'Ball', 'A round object used in many sports', 'easy'),
(3, 'Cat', 'A small domesticated carnivorous mammal', 'easy'),
(4, 'Dog', 'Man\'s best friend', 'easy'),
(5, 'Egg', 'Laid by hens, often eaten for breakfast', 'easy'),
(6, 'Fish', 'An animal that swims in water', 'easy'),
(7, 'Grass', 'Green stuff that grows on the lawn', 'easy'),
(8, 'Hat', 'Something you wear on your head', 'easy'),
(9, 'Ice', 'Frozen water', 'easy'),
(10, 'Jam', 'A sweet spread made from fruit and sugar', 'easy'),
(11, 'Kite', 'A toy that flies in the wind', 'easy'),
(12, 'Lamp', 'A device that gives off light', 'easy'),
(13, 'Moon', 'Earth\'s natural satellite', 'easy'),
(14, 'Nest', 'A bird\'s home', 'easy'),
(15, 'Orange', 'A citrus fruit and a color', 'easy'),
(16, 'Pen', 'A tool used for writing', 'easy'),
(17, 'Queen', 'A female monarch', 'easy'),
(18, 'Rain', 'Water that falls from the sky', 'easy'),
(19, 'Sun', 'The star at the center of our solar system', 'easy'),
(20, 'Tree', 'A tall plant with a trunk and branches', 'easy'),
(21, 'Umbrella', 'A tool used to stay dry in the rain', 'easy'),
(22, 'Van', 'A type of vehicle', 'easy'),
(23, 'Water', 'A liquid essential for life', 'easy'),
(24, 'X-ray', 'A type of imaging used in medicine', 'easy'),
(25, 'Yellow', 'The color of bananas', 'easy'),
(26, 'Zoo', 'A place where animals are kept and displayed', 'easy'),
(27, 'Boat', 'A vehicle that travels on water', 'easy'),
(28, 'Cake', 'A sweet baked dessert', 'easy'),
(29, 'Duck', 'A water bird with a broad flat bill', 'easy'),
(30, 'Ear', 'The organ used for hearing', 'easy'),
(31, 'Frog', 'A small amphibian that can jump', 'easy'),
(32, 'Goat', 'A domesticated animal with horns', 'easy'),
(33, 'Horse', 'A large animal often ridden by people', 'easy'),
(34, 'Ink', 'A liquid used in pens for writing', 'easy'),
(35, 'Juice', 'A drink made from fruit', 'easy'),
(36, 'Key', 'A tool used to open locks', 'easy'),
(37, 'Lion', 'The king of the jungle', 'easy'),
(38, 'Milk', 'A white liquid produced by mammals', 'easy'),
(39, 'Nose', 'The part of the face used for smelling', 'easy'),
(40, 'Owl', 'A nocturnal bird of prey', 'easy'),
(41, 'Pig', 'A farm animal that says \"oink\"', 'easy'),
(42, 'Quack', 'The sound a duck makes', 'easy'),
(43, 'Rock', 'A solid material found in nature', 'easy'),
(44, 'Star', 'A bright object in the night sky', 'easy'),
(45, 'Tiger', 'A large wild cat with stripes', 'easy'),
(46, 'Umbra', 'The dark part of a shadow', 'easy'),
(47, 'Violin', 'A stringed musical instrument', 'easy'),
(48, 'Wind', 'Air in motion', 'easy'),
(49, 'Xylophone', 'A musical instrument with wooden bars', 'easy'),
(50, 'Yolk', 'The yellow part of an egg', 'easy'),
(51, 'Zebra', 'A striped animal found in Africa', 'easy'),
(52, 'Ant', 'A small insect that lives in colonies', 'easy'),
(53, 'Bread', 'A staple food made from flour and water', 'easy'),
(54, 'Car', 'A vehicle with four wheels', 'easy'),
(55, 'Door', 'A hinged barrier used to close an opening in a wall', 'easy'),
(56, 'Elephant', 'The largest land animal', 'easy'),
(57, 'Flag', 'A piece of cloth with a symbol or design, used as a signal', 'easy'),
(58, 'Gate', 'A hinged barrier used to close an opening in a fence or wall', 'easy'),
(59, 'House', 'A building for human habitation', 'easy'),
(60, 'Igloo', 'A dome-shaped ice house built by Eskimos', 'easy'),
(61, 'Jacket', 'A garment worn on the upper body for warmth', 'easy'),
(62, 'Kangaroo', 'A large marsupial from Australia', 'easy'),
(63, 'Lemon', 'A sour yellow citrus fruit', 'easy'),
(64, 'Mouse', 'A small rodent', 'easy'),
(65, 'Nut', 'A hard-shelled fruit', 'easy'),
(66, 'Octopus', 'A sea creature with eight arms', 'easy'),
(67, 'Pan', 'A shallow cooking container', 'easy'),
(68, 'Quilt', 'A warm bed covering made of stitched fabric', 'easy'),
(69, 'Rose', 'A type of flower often given as a symbol of love', 'easy'),
(70, 'Snake', 'A legless reptile', 'easy'),
(71, 'Tomato', 'A red fruit often used in salads', 'easy'),
(72, 'Umbrella', 'A device used for protection from rain', 'easy'),
(73, 'Vase', 'A container used for holding flowers', 'easy'),
(74, 'Wolf', 'A wild carnivorous mammal', 'easy'),
(75, 'Xerox', 'A brand of photocopiers', 'easy'),
(76, 'Yak', 'A long-haired domesticated bovid found in the Himalayas', 'easy'),
(77, 'Zero', 'The number before one', 'easy'),
(78, 'Balloon', 'A flexible bag filled with air or gas', 'easy'),
(79, 'Candy', 'A sweet confection', 'easy'),
(80, 'Drum', 'A percussion instrument', 'easy'),
(81, 'Elbow', 'The joint between the forearm and upper arm', 'easy'),
(82, 'Feather', 'A flat appendage found on the bodies of birds', 'easy'),
(83, 'Giraffe', 'The tallest animal on land', 'easy'),
(84, 'Hammer', 'A tool used for hitting nails', 'easy'),
(85, 'Ice', 'Water in solid form', 'easy'),
(86, 'Juice', 'Liquid extracted from fruits or vegetables', 'easy'),
(87, 'Kettle', 'A container used for boiling water', 'easy'),
(88, 'Ladder', 'A piece of equipment used for climbing', 'easy'),
(89, 'Moon', 'A natural satellite of the Earth', 'easy'),
(90, 'Nest', 'A structure built by birds for laying eggs', 'easy'),
(91, 'Oven', 'An appliance used for baking or roasting', 'easy'),
(92, 'Pencil', 'An instrument for writing or drawing', 'easy'),
(93, 'Quokka', 'A small marsupial native to Australia', 'easy'),
(94, 'River', 'A large natural stream of water', 'easy'),
(95, 'Sun', 'The star that provides light to the Earth', 'easy'),
(96, 'Tree', 'A large plant with a trunk and branches', 'easy'),
(97, 'Umbrella', 'A tool used to protect against rain', 'easy'),
(98, 'Volcano', 'A mountain that erupts with lava', 'easy'),
(99, 'Windmill', 'A structure that converts wind into energy', 'easy'),
(100, 'Xylophone', 'A musical instrument with bars struck by mallets', 'easy'),
(101, 'Absurd', 'Something wildly unreasonable', 'medium'),
(102, 'Blurry', 'Not clearly visible', 'medium'),
(103, 'Cactus', 'A spiky desert plant', 'medium'),
(104, 'Decade', 'A period of ten years', 'medium'),
(105, 'Fossil', 'Preserved remains of a prehistoric organism', 'medium'),
(106, 'Glimpse', 'A quick look', 'medium'),
(107, 'Humble', 'Modest or low view of one\'s importance', 'medium'),
(108, 'Jaguar', 'A large wild cat', 'medium'),
(109, 'Linger', 'To stay in a place longer than necessary', 'medium'),
(110, 'Majesty', 'Impressive beauty or dignity', 'medium'),
(111, 'Nebula', 'A cloud of gas and dust in space', 'medium'),
(112, 'Opaque', 'Not allowing light to pass through', 'medium'),
(113, 'Pirate', 'A person who attacks and robs ships at sea', 'medium'),
(114, 'Quartz', 'A hard mineral, often used in jewelry', 'medium'),
(115, 'Rumble', 'A continuous deep, resonant sound', 'medium'),
(116, 'Serene', 'Calm, peaceful, and untroubled', 'medium'),
(117, 'Tundra', 'A vast, flat, treeless Arctic region', 'medium'),
(118, 'Umbra', 'The fully shaded inner region of a shadow', 'medium'),
(119, 'Verdict', 'A decision on a disputed issue in a court of law', 'medium'),
(120, 'Whistle', 'A clear, high-pitched sound', 'medium'),
(121, 'Xylophone', 'A musical instrument with wooden bars', 'medium'),
(122, 'Yonder', 'At some distance in the direction indicated', 'medium'),
(123, 'Zephyr', 'A gentle, mild breeze', 'medium'),
(124, 'Beacon', 'A fire or light set up in a high position as a warning', 'medium'),
(125, 'Cascade', 'A small waterfall', 'medium'),
(126, 'Dazzle', 'To blind someone temporarily with a bright light', 'medium'),
(127, 'Embrace', 'To hold someone closely in one\'s arms', 'medium'),
(128, 'Frenzy', 'A state of uncontrolled excitement', 'medium'),
(129, 'Glisten', 'To shine with a sparkling light', 'medium'),
(130, 'Horizon', 'The line where the earth\'s surface and the sky appear to meet', 'medium'),
(131, 'Impose', 'To force something to be accepted', 'medium'),
(132, 'Jungle', 'An area of dense, tropical vegetation', 'medium'),
(133, 'Knight', 'A man who served his sovereign or lord as a mounted soldier', 'medium'),
(134, 'Lantern', 'A lamp with a transparent case for protecting the flame or light', 'medium'),
(135, 'Mosaic', 'A picture or pattern created by arranging small colored pieces', 'medium'),
(136, 'Nimble', 'Quick and light in movement', 'medium'),
(137, 'Oracle', 'A priest or priestess acting as a medium for a god', 'medium'),
(138, 'Puzzle', 'A game or problem that tests ingenuity or knowledge', 'medium'),
(139, 'Quaint', 'Attractively unusual or old-fashioned', 'medium'),
(140, 'Radiant', 'Sending out light; shining or glowing brightly', 'medium'),
(141, 'Stellar', 'Related to stars or outstandingly good', 'medium'),
(142, 'Tangle', 'To twist together into a confused mass', 'medium'),
(143, 'Unveil', 'To show or reveal something for the first time', 'medium'),
(144, 'Vortex', 'A whirling mass of fluid or air', 'medium'),
(145, 'Wander', 'To move or travel slowly, without a fixed purpose or direction', 'medium'),
(146, 'Xenon', 'A chemical element, noble gas', 'medium'),
(147, 'Yield', 'To give way to arguments, demands, or pressure', 'medium'),
(148, 'Zealot', 'A person who is fanatical about their beliefs', 'medium'),
(149, 'Banish', 'To send someone away from a place as a punishment', 'medium'),
(150, 'Candid', 'Truthful and straightforward', 'medium'),
(151, 'Dismay', 'To cause someone to feel distress', 'medium'),
(152, 'Eclipse', 'An event where one object in space casts a shadow on another', 'medium'),
(153, 'Fragment', 'A small part broken off something', 'medium'),
(154, 'Grumble', 'To complain or protest about something in a bad-tempered but typically muted way', 'medium'),
(155, 'Harness', 'To control and make use of natural resources, especially to produce energy', 'medium'),
(156, 'Ignite', 'To catch fire or cause to catch fire', 'medium'),
(157, 'Jargon', 'Special words or expressions used by a profession or group', 'medium'),
(158, 'Kinetic', 'Relating to or resulting from motion', 'medium'),
(159, 'Liaison', 'A person who acts as a link to assist communication or cooperation between groups', 'medium'),
(160, 'Murmur', 'A low continuous background noise', 'medium'),
(161, 'Nostalgia', 'A sentimental longing for the past', 'medium'),
(162, 'Obscure', 'Not discovered or known about; uncertain', 'medium'),
(163, 'Pinnacle', 'The most successful point; the culmination', 'medium'),
(164, 'Quiver', 'To tremble or shake with a slight rapid motion', 'medium'),
(165, 'Reverie', 'A state of being pleasantly lost in one\'s thoughts; a daydream', 'medium'),
(166, 'Sublime', 'Of such excellence or beauty as to inspire great admiration or awe', 'medium'),
(167, 'Tranquil', 'Free from disturbance; calm', 'medium'),
(168, 'Uncanny', 'Strange or mysterious, especially in an unsettling way', 'medium'),
(169, 'Venture', 'A risky or daring journey or undertaking', 'medium'),
(170, 'Whimsical', 'Playfully quaint or fanciful, especially in an appealing and amusing way', 'medium'),
(171, 'Yearn', 'To have an intense feeling of longing for something', 'medium'),
(172, 'Zodiac', 'A belt of the heavens divided into twelve signs', 'medium'),
(173, 'Antique', 'A collectible object such as a piece of furniture or work of art that has a high value because of its age', 'medium'),
(174, 'Baffle', 'To totally bewilder or perplex', 'medium'),
(175, 'Courage', 'The ability to do something that frightens one; bravery', 'medium'),
(176, 'Distant', 'Far away in space or time', 'medium'),
(177, 'Evoke', 'To bring or recall to the conscious mind', 'medium'),
(178, 'Fickle', 'Changing frequently, especially as regards one\'s loyalties, interests, or affection', 'medium'),
(179, 'Gamble', 'To take risky action in the hope of a desired result', 'medium'),
(180, 'Hollow', 'Having a hole or empty space inside', 'medium'),
(181, 'Insomnia', 'Habitual sleeplessness; inability to sleep', 'medium'),
(182, 'Jovial', 'Cheerful and friendly', 'medium'),
(183, 'Kinship', 'Blood relationship', 'medium'),
(184, 'Luster', 'A gentle sheen or soft glow', 'medium'),
(185, 'Meander', 'To follow a winding course', 'medium'),
(186, 'Nimble', 'Quick and light in movement or action', 'medium'),
(187, 'Omen', 'An event regarded as a portent of good or evil', 'medium'),
(188, 'Placid', 'Not easily upset or excited', 'medium'),
(189, 'Quench', 'To satisfy one\'s thirst by drinking', 'medium'),
(190, 'Rustic', 'Relating to the countryside; rural', 'medium'),
(191, 'Splendid', 'Magnificent; very impressive', 'medium'),
(192, 'Timid', 'Showing a lack of courage or confidence; easily frightened', 'medium'),
(193, 'Uplift', 'To raise the level of; improve', 'medium'),
(194, 'Vivid', 'Producing powerful feelings or strong, clear images in the mind', 'medium'),
(195, 'Wistful', 'Having or showing a feeling of vague or regretful longing', 'medium'),
(196, 'Yielding', 'Giving way under pressure; not hard or rigid', 'medium'),
(197, 'Zealous', 'Having or showing zeal; enthusiastic and eager', 'medium'),
(198, 'Whisker', 'A long, projecting hair or bristle', 'medium'),
(199, 'Xenial', 'Relating to hospitality or friendly relations between host and guest', 'medium'),
(200, 'Yacht', 'A medium-sized sailboat equipped for cruising or racing', 'medium'),
(201, 'Abyss', 'A deep or seemingly bottomless chasm', 'hard'),
(202, 'Berserk', 'Out of control with anger or excitement; wild or frenzied', 'hard'),
(203, 'Cryptic', 'Having a meaning that is mysterious or obscure', 'hard'),
(204, 'Dichotomy', 'A division or contrast between two things that are or are represented as being opposed or entirely different', 'hard'),
(205, 'Ephemeral', 'Lasting for a very short time', 'hard'),
(206, 'Furtive', 'Attempting to avoid notice or attention, typically because of guilt or a belief that discovery would lead to trouble', 'hard'),
(207, 'Gargantuan', 'Enormous', 'hard'),
(208, 'Hapless', 'Unfortunate', 'hard'),
(209, 'Ineffable', 'Too great or extreme to be expressed or described in words', 'hard'),
(210, 'Juxtapose', 'To place or deal with close together for contrasting effect', 'hard'),
(211, 'Kafkaesque', 'Marked by a senseless, disorienting, often menacing complexity', 'hard'),
(212, 'Labyrinthine', 'Irregular and twisting, like a labyrinth', 'hard'),
(213, 'Machiavellian', 'Cunning, scheming, and unscrupulous, especially in politics', 'hard'),
(214, 'Nebulous', 'In the form of a cloud or haze; hazy', 'hard'),
(215, 'Obfuscate', 'To render obscure, unclear, or unintelligible', 'hard'),
(216, 'Palimpsest', 'A manuscript or piece of writing material on which the original writing has been effaced to make room for later writing but of which traces remain', 'hard'),
(217, 'Quagmire', 'A soft boggy area of land that gives way underfoot; a difficult, precarious, or entrapping position', 'hard'),
(218, 'Raconteur', 'A person who tells anecdotes in a skillful and amusing way', 'hard'),
(219, 'Sycophant', 'A person who acts obsequiously toward someone important in order to gain advantage', 'hard'),
(220, 'Taciturn', 'Reserved or uncommunicative in speech; saying little', 'hard'),
(221, 'Ubiquitous', 'Present, appearing, or found everywhere', 'hard'),
(222, 'Vexillology', 'The study of flags', 'hard'),
(223, 'Whimsical', 'Playfully quaint or fanciful, especially in an appealing and amusing way', 'hard'),
(224, 'Xenophobia', 'Dislike of or prejudice against people from other countries', 'hard'),
(225, 'Zephyr', 'A soft gentle breeze', 'hard'),
(226, 'Agnostic', 'A person who believes that nothing is known or can be known of the existence or nature of God', 'hard'),
(227, 'Blasphemy', 'The act or offense of speaking sacrilegiously about God or sacred things; profane talk', 'hard'),
(228, 'Conundrum', 'A confusing and difficult problem or question', 'hard'),
(229, 'Diatribe', 'A forceful and bitter verbal attack against someone or something', 'hard'),
(230, 'Ebullient', 'Cheerful and full of energy', 'hard'),
(231, 'Flabbergast', 'To surprise someone greatly; astonish', 'hard'),
(232, 'Gossamer', 'A fine, filmy substance consisting of cobwebs spun by small spiders, seen especially in autumn', 'hard'),
(233, 'Harbinger', 'A person or thing that announces or signals the approach of another', 'hard'),
(234, 'Iconoclast', 'A person who attacks or criticizes cherished beliefs or institutions', 'hard'),
(235, 'Juggernaut', 'A huge, powerful, and overwhelming force or institution', 'hard'),
(236, 'Kaleidoscope', 'A constantly changing pattern or sequence of elements', 'hard'),
(237, 'Lugubrious', 'Looking or sounding sad and dismal', 'hard'),
(238, 'Maelstrom', 'A powerful whirlpool in the sea or a river', 'hard'),
(239, 'Nefarious', 'Wicked or criminal', 'hard'),
(240, 'Ostentatious', 'Characterized by vulgar or pretentious display; designed to impress or attract notice', 'hard'),
(241, 'Pandemonium', 'Wild and noisy disorder or confusion; uproar', 'hard'),
(242, 'Querulous', 'Complaining in a petulant or whining manner', 'hard'),
(243, 'Recalcitrant', 'Having an obstinately uncooperative attitude toward authority or discipline', 'hard'),
(244, 'Surreptitious', 'Kept secret, especially because it would not be approved of', 'hard'),
(245, 'Tenebrous', 'Dark; shadowy or obscure', 'hard'),
(246, 'Unfathomable', 'Incapable of being fully explored or understood', 'hard'),
(247, 'Vicissitude', 'A change of circumstances or fortune, typically one that is unwelcome or unpleasant', 'hard'),
(248, 'Wunderkind', 'A person who achieves great success when relatively young', 'hard'),
(249, 'Xylophone', 'A musical instrument in the percussion family that consists of wooden bars struck by mallets', 'hard'),
(250, 'Yammer', 'To talk volubly, persistently, and often loudly', 'hard'),
(251, 'Ziggurat', 'A rectangular stepped tower, sometimes surmounted by a temple', 'hard'),
(252, 'Alacrity', 'Brisk and cheerful readiness', 'hard'),
(253, 'Brouhaha', 'A noisy and overexcited reaction or response to something', 'hard'),
(254, 'Cacophony', 'A harsh, discordant mixture of sounds', 'hard'),
(255, 'Deleterious', 'Causing harm or damage', 'hard'),
(256, 'Ebullition', 'A sudden outburst of emotion or violence', 'hard'),
(257, 'Felicity', 'Intense happiness; the ability to find appropriate expression for one\'s thoughts', 'hard'),
(258, 'Grandiloquent', 'Pompous or extravagant in language, style, or manner', 'hard'),
(259, 'Hegemony', 'Leadership or dominance, especially by one country or social group over others', 'hard'),
(260, 'Indefatigable', 'Persisting tirelessly', 'hard'),
(261, 'Jeopardy', 'Danger of loss, harm, or failure', 'hard'),
(262, 'Knavery', 'Action or practice characteristic of a knave; dishonesty or crafty dealing', 'hard'),
(263, 'Lachrymose', 'Tearful or given to weeping', 'hard'),
(264, 'Misanthrope', 'A person who dislikes humankind and avoids human society', 'hard'),
(265, 'Nugatory', 'Of no value or importance', 'hard'),
(266, 'Obstreperous', 'Noisy and difficult to control', 'hard'),
(267, 'Pulchritude', 'Beauty', 'hard'),
(268, 'Quixotic', 'Exceedingly idealistic; unrealistic and impractical', 'hard'),
(269, 'Reverberate', 'To be repeated several times as an echo', 'hard'),
(270, 'Sesquipedalian', 'Given to using long words', 'hard'),
(271, 'Tergiversate', 'To make conflicting or evasive statements; equivocate', 'hard'),
(272, 'Uxorious', 'Having or showing an excessive or submissive fondness for one\'s wife', 'hard'),
(273, 'Verisimilitude', 'The appearance of being true or real', 'hard'),
(274, 'Wheedle', 'To employ endearments or flattery to persuade someone to do something or give one something', 'hard'),
(275, 'Xenogenesis', 'The supposed production of offspring markedly different from the parent', 'hard'),
(276, 'Yokel', 'An uneducated and unsophisticated person from the countryside', 'hard'),
(277, 'Zoology', 'The scientific study of the behavior, structure, physiology, classification, and distribution of animals', 'hard'),
(278, 'Acrimony', 'Bitterness or ill feeling', 'hard'),
(279, 'Bifurcate', 'To divide into two branches or forks', 'hard'),
(280, 'Churlish', 'Rude in a mean-spirited and surly way', 'hard'),
(281, 'Defenestration', 'The act of throwing someone out of a window', 'hard'),
(282, 'Ebullience', 'The quality of being cheerful and full of energy; exuberance', 'hard'),
(283, 'Feckless', 'Lacking initiative or strength of character; irresponsible', 'hard'),
(284, 'Gallimaufry', 'A confused jumble or medley of things', 'hard'),
(285, 'Hippopotamus', 'A large, thick-skinned, semi-aquatic African mammal', 'hard'),
(286, 'Intransigent', 'Unwilling or refusing to change one\'s views or to agree about something', 'hard'),
(287, 'Juggernaut', 'A huge, powerful, and overwhelming force or institution', 'hard'),
(288, 'Knell', 'The sound of a bell, especially when rung solemnly for a death or funeral', 'hard'),
(289, 'Lugubrious', 'Looking or sounding sad and dismal', 'hard'),
(290, 'Munificent', 'More generous than is usual or necessary', 'hard'),
(291, 'Nihilism', 'The rejection of all religious and moral principles, often in the belief that life is meaningless', 'hard'),
(292, 'Obdurate', 'Stubbornly refusing to change one\'s opinion or course of action', 'hard'),
(293, 'Perspicacious', 'Having a ready insight into and understanding of things', 'hard'),
(294, 'Quarantine', 'A state, period, or place of isolation in which people or animals that have arrived from elsewhere or been exposed to infectious or contagious disease are placed', 'hard'),
(295, 'Recalcitrant', 'Having an obstinately uncooperative attitude toward authority or discipline', 'hard'),
(296, 'Soporific', 'Tending to induce drowsiness or sleep', 'hard'),
(297, 'Truculent', 'Eager or quick to argue or fight; aggressively defiant', 'hard'),
(298, 'Ubiquitous', 'Present, appearing, or found everywhere', 'hard'),
(299, 'Vicarious', 'Experienced in the imagination through the feelings or actions of another person', 'hard'),
(300, 'Weltanschauung', 'A particular philosophy or view of life; the worldview of an individual or group', 'hard');

-- --------------------------------------------------------

--
-- Table structure for table `login_details`
--

CREATE TABLE `login_details` (
  `Name` varchar(20) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `mail` longtext NOT NULL,
  `phoneNumber` bigint(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `creditPoints` int(11) NOT NULL,
  `personType` varchar(20) NOT NULL DEFAULT 'player',
  `coinsEarned` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `login_details`
--

INSERT INTO `login_details` (`Name`, `Username`, `mail`, `phoneNumber`, `password`, `creditPoints`, `personType`, `coinsEarned`) VALUES
('hardik', 'hardik38', 'er.hardik@gmail.com', 8787878787, '123123', 100, 'player', 0),
('Satvik Parihar', 'Ichigo', 'harishparihar663@gmail.com', 9636092499, 'Satvik*1234', 0, 'admin', 0),
('Satvik Parihar', 'Ichigo_xk', 'vedansh.dubai@gmail.com', 9950261299, 'Satvik*17', 999999999, 'admin', 200),
('Sp', 'Satvik', 'ac', 1231231231, 'sp2005', 2500, 'player', 0);

-- --------------------------------------------------------

--
-- Table structure for table `quizgamelogs`
--

CREATE TABLE `quizgamelogs` (
  `Username` varchar(50) DEFAULT NULL,
  `TimeOfPlaying` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CreditsUsed` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tickingtimelogs`
--

CREATE TABLE `tickingtimelogs` (
  `Username` varchar(50) DEFAULT NULL,
  `TimeOfPlaying` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CreditsUsed` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tickingtimelogs`
--

INSERT INTO `tickingtimelogs` (`Username`, `TimeOfPlaying`, `CreditsUsed`) VALUES
('Satvik', '2024-08-23 03:48:56', 300),
('Satvik', '2024-08-23 04:04:21', 300);

-- --------------------------------------------------------

--
-- Table structure for table `tictactoelogs`
--

CREATE TABLE `tictactoelogs` (
  `Username` varchar(50) DEFAULT NULL,
  `TimeOfPlaying` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `CreditsUsed` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tictactoelogs`
--

INSERT INTO `tictactoelogs` (`Username`, `TimeOfPlaying`, `CreditsUsed`) VALUES
('Satvik', '2024-08-23 03:52:49', 500),
('Satvik', '2024-08-23 04:06:48', 500),
('Satvik', '2024-08-23 04:14:03', 500);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `available_games`
--
ALTER TABLE `available_games`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `captcha_codes`
--
ALTER TABLE `captcha_codes`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `digicricketlogs`
--
ALTER TABLE `digicricketlogs`
  ADD KEY `Username` (`Username`);

--
-- Indexes for table `hangman_words`
--
ALTER TABLE `hangman_words`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login_details`
--
ALTER TABLE `login_details`
  ADD PRIMARY KEY (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `available_games`
--
ALTER TABLE `available_games`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `hangman_words`
--
ALTER TABLE `hangman_words`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=502;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `digicricketlogs`
--
ALTER TABLE `digicricketlogs`
  ADD CONSTRAINT `digicricketlogs_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `login_details` (`Username`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
