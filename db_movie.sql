-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2019 at 04:48 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_movie`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_movie`
--

CREATE TABLE `data_movie` (
  `title` text NOT NULL,
  `description` text NOT NULL,
  `release_date` date NOT NULL,
  `cover` text NOT NULL,
  `trailer` text NOT NULL,
  `id` int(11) NOT NULL,
  `genre` text NOT NULL,
  `poster` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data_movie`
--

INSERT INTO `data_movie` (`title`, `description`, `release_date`, `cover`, `trailer`, `id`, `genre`, `poster`) VALUES
('Aquaman', 'Once home to the most advanced civilization on Earth, the city of Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people -- and then the surface world. Standing in his way is Aquaman, Orm\'s half-human, half-Atlantean brother and true heir to the throne. With help from royal counselor Vulko, Aquaman must retrieve the legendary Trident of Atlan and embrace his destiny as protector of the deep.', '2018-12-07', 'https://i.ytimg.com/vi/WDkg3h8PCVU/maxresdefault.jpg', 'https://www.youtube.com/watch?v=WDkg3h8PCVU', 1, 'Action', 'https://cdn1.thr.com/sites/default/files/imagecache/NFE_portrait/2018/07/aquaman_poster_-_screengrab_-_p_2018.jpg'),
('Bumblebee', 'On the run in the year 1987, Bumblebee the Autobot seeks refuge in a junkyard in a small California beach town. Charlie, on the brink of turning 18 years old and trying to find her place in the world, soon discovers the battle-scarred and broken Bumblebee. When Charlie revives him, she quickly learns that this is no ordinary yellow Volkswagen.', '2018-12-03', 'https://i.ytimg.com/vi/lcwmDAYt22k/maxresdefault.jpg', 'https://www.youtube.com/watch?v=lcwmDAYt22k', 3, 'Science Fiction', 'https://oyster.ignimgs.com/wordpress/stg.ign.com/2018/08/TFBEE_INTL_TSR_CROUCHING_BB_DGTL_1_SHT_UK-1-720x1123.jpg'),
('Spiderman into the spider verse', 'Bitten by a radioactive spider in the subway, Brooklyn teenager Miles Morales suddenly develops mysterious powers that transform him into the one and only Spider-Man. When he meets Peter Parker, he soon realizes that there are many others who share his special, high-flying talents. Miles must now use his newfound skills to battle the evil Kingpin, a hulking madman who can open portals to other universes and pull different versions of Spider-Man into our world.', '2018-12-12', 'http://intothespiderverse.movie/images/share/shareImage.jpg', 'https://www.youtube.com/watch?v=g4Hbz2jLxvQ', 4, 'Fantasy', 'https://m.media-amazon.com/images/M/MV5BMjMwNDkxMTgzOF5BMl5BanBnXkFtZTgwNTkwNTQ3NjM@._V1_UX182_CR0,0,182,268_AL_.jpg'),
('Bohemian Rhapsody', 'Bohemian Rhapsody is a foot-stomping celebration of Queen, their music and their extraordinary lead singer Freddie Mercury. Freddie defied stereotypes and shattered convention to become one of the most beloved entertainers on the planet. The film traces the meteoric rise of the band through their iconic songs and revolutionary sound. They reach unparalleled success, but in an unexpected turn Freddie, surrounded by darker influences, shuns Queen in pursuit of his solo career. Having suffered greatly without the collaboration of Queen, Freddie manages to reunite with his bandmates just in time for Live Aid. While bravely facing a recent AIDS diagnosis, Freddie leads the band in one of the greatest performances in the history of rock music. Queen cements a legacy that continues to inspire outsiders, dreamers and music lovers to this day', '2018-10-24', 'https://e3.365dm.com/18/05/1096x616/skynews-freddie-mercury-bohemian-rhapsody_4311164.jpg?20181023231559', 'https://www.youtube.com/watch?v=mP0VHJYFOAU', 7, 'Biography', 'https://i.pinimg.com/originals/e6/0e/91/e60e913556d164b415b5cce9afb796ce.jpg'),
('A Star Is Born', 'Seasoned musician Jackson Maine discovers -- and falls in love with -- struggling artist Ally. She has just about given up on her dream to make it big as a singer until Jackson coaxes her into the spotlight. But even as Ally\'s career takes off, the personal side of their relationship is breaking down, as Jackson fights an ongoing battle with his own internal demons.', '2018-10-19', 'https://www.landmarkcinemas.com/media/391954/1280x720-starisborn.jpg', 'https://www.youtube.com/watch?v=5vheNbQlsyU', 8, 'Drama', 'https://i.pinimg.com/736x/a4/db/5a/a4db5ac360c5469e3538d07bda7d75c0.jpg'),
('Mary Poppins Returns', 'Now an adult with three children, bank teller Michael Banks learns that his house will be repossessed in five days unless he can pay back a loan. His only hope is to find a missing certificate that shows proof of valuable shares that his father left him years earlier. Just as all seems lost, Michael and his sister receive the surprise of a lifetime when Mary Poppins -- the beloved nanny from their childhood -- arrives to save the day and take the Banks family on a magical, fun-filled adventure.', '2018-11-29', 'https://cnet3.cbsistatic.com/img/fV0p7LFXtrMUZV9ARNXkgBabaw0=/0x293:864x763/1600x900/2018/09/17/32f45420-8126-4fd3-a9e6-95747fd2c719/mary-poppins-returns-poster-emily-blunt.jpg', 'https://www.youtube.com/watch?v=-3jsfXDZLIY', 9, 'Fantasy', 'https://www.bleedingcool.com/wp-content/uploads/2018/11/Mary-Poppins-Returns.jpg?x70969'),
('Captain Marvel', 'Captain Marvel gets caught in the middle of a galactic war between two alien races.', '2019-03-08', 'https://i.ytimg.com/vi/0LHxvxdRnYc/maxresdefault.jpg', 'https://www.youtube.com/watch?v=Z1BCujX3pw8', 10, 'Action', 'https://img.purch.com/w/640/aHR0cDovL3d3dy5zcGFjZS5jb20vaW1hZ2VzL2kvMDAwLzA4Mi8wOTQvaTAyL2NhcHRhaW4tbWFydmVsLXBvc3Rlci0xLmpwZz8xNTQ2OTg2NzU4'),
('Ralph Breaks the Internet', 'Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope\'s video game, \"Sugar Rush.\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet -- the netizens -- to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.', '2018-11-23', 'https://akns-images.eonline.com/eol_images/Entire_Site/20181020/rs_1024x576-181120121508-1024.ralph-breaks-the-internet-5.112018.jpg?fit=inside|900:auto&output-quality=90', 'https://www.youtube.com/watch?v=T73h5bmD8Dc', 11, 'Fantasy', 'https://images-na.ssl-images-amazon.com/images/I/717mu1SRNAL._SY679_.jpg'),
('Avengers: Endgame', 'Adrift in space with no food or water, Tony Stark sends a message to Pepper Potts as his oxygen supply starts to dwindle. Meanwhile, the remaining Avengers -- Thor, Black Widow, Captain America and Bruce Banner -- must figure out a way to bring back their vanquished allies for an epic showdown with Thanos -- the evil demigod who decimated the planet and the universe.', '2019-04-26', 'https://cdn.abcotvs.com/dip/images/4868144_120918-cc-avengers-title-treatment-img.jpg', 'https://www.youtube.com/watch?v=hA6hldpSTF8', 12, 'Action', 'https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/intermediary/f/74732bae-418f-4b43-8321-8d2876f7de4d/dch7nw5-6bbf7c67-f2e3-4259-862b-88365c300e10.jpg/v1/fill/w_742,h_1077,q_70,strp/avengers_4___endgame__2019__poster_by_midiya42_dch7nw5-pre.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'Qubay', 'rahasia'),
(2, 'Dono', 'donojuga'),
(6, 'Sath', 'sath'),
(7, 'Redho', 'kuantum'),
(8, 'Kara', 'kara'),
(9, 'Anon', 'anon123'),
(12, 'Siwa', 'bener'),
(13, 'Bolang', 'bolang1'),
(14, 'User', 'user'),
(15, 'Deta', 'deta1');

-- --------------------------------------------------------

--
-- Table structure for table `user_review`
--

CREATE TABLE `user_review` (
  `id_movie` int(11) NOT NULL,
  `id_review` int(11) NOT NULL,
  `rating` float NOT NULL,
  `review` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_review`
--

INSERT INTO `user_review` (`id_movie`, `id_review`, `rating`, `review`, `created_at`, `id_user`) VALUES
(12, 5, 4.5, 'BEST SUPERHERO MOVIE OF ALL TIME!! Mom & KIDS AGREE!\r\nThe Best Family Movie we could see. I went at midnight to see with my 14 yr old. ', '2019-02-07 13:55:09', 7),
(7, 18, 4.5, 'We went to preview the movie for our 9 yo daughter who performs Queen songs and has been counting the days until the release of this movie. First, let me say that we loved the movie!!! It was incredible!!! ', '2019-02-07 16:37:31', 2),
(9, 19, 2, 'First, the movie was LONG. without previews it\'s over 2 hours long. for kids in my opinion, it\'s too long. the movie was different than the orginal in these ways. It is scary and dark in several scenes.', '2019-02-07 16:57:58', 6),
(4, 20, 4.5, 'Perfectly done superhero movie has plenty of action despite PG rating\r\nSpider-man: Into the Spider-verse is a really well done superhero film with a great story and characters. The animation really impressed me. There was a great sense of humor along with the action forward story line.', '2019-02-07 16:59:14', 2),
(1, 21, 5, 'MIND BLOWN, actually amazing!!\r\nThis was a faultless movie. The story line was amazing, the actors are perfect for the role, its a perfect family movie, and you should definitely go watch it!!', '2019-02-07 17:00:44', 1),
(3, 24, 3, 'There\'re some scenes violent for children under 10, so I want to mention them for other families; 1. Some Decepticons kill Autobots by cutting the bodies completely to the half. Since they\'re not humans, no blood and doesn\'t appeal as violent, but want you to know.', '2019-02-07 17:03:23', 6),
(8, 25, 5, 'A Star Is Born is Fantastic, Moving, Mature\r\nA Star Is Born was a fantastic movie, one of my favorites of the year. The music was amazing, the acting was equally impressive, yet there was plenty of profanity, sexuality, and some nudity. Violence: a man punches and is abusive while drunk many times in the movie.', '2019-02-07 17:05:32', 9),
(11, 27, 3.5, 'Disney\'s charming, insightful sequel shines a light on the wonders and horrors of the internet, from the camaraderie of sharing silly viral videos to the vicious nature of social media comments.', '2019-02-07 17:15:33', 1),
(11, 29, 1, 'Jelek gabut', '2019-02-17 03:30:43', 15),
(1, 30, 5, 'Udhreheh', '2019-02-17 04:07:34', 15);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_movie`
--
ALTER TABLE `data_movie`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_review`
--
ALTER TABLE `user_review`
  ADD PRIMARY KEY (`id_review`),
  ADD KEY `id_movie` (`id_movie`),
  ADD KEY `user_review_ibfk_1` (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_movie`
--
ALTER TABLE `data_movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `user_review`
--
ALTER TABLE `user_review`
  MODIFY `id_review` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_review`
--
ALTER TABLE `user_review`
  ADD CONSTRAINT `user_review_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
