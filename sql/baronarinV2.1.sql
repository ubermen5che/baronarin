CREATE DATABASE  IF NOT EXISTS `security` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `security`;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: security
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Article`
--

DROP TABLE IF EXISTS `Article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Article` (
  `id` bigint NOT NULL,
  `createDate` datetime(6) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_size` bigint NOT NULL,
  `orig_name` varchar(255) DEFAULT NULL,
  `orig_papername` varchar(255) DEFAULT NULL,
  `papername` varchar(255) DEFAULT NULL,
  `people1_email` varchar(255) DEFAULT NULL,
  `people1_encrypt` text,
  `people1_name` varchar(255) DEFAULT NULL,
  `people1_num` varchar(255) DEFAULT NULL,
  `people1_sign` varchar(255) DEFAULT NULL,
  `people1_signname` varchar(255) DEFAULT NULL,
  `people1_time` varchar(255) DEFAULT NULL,
  `people2_email` varchar(255) DEFAULT NULL,
  `people2_encrypt` text,
  `people2_name` varchar(255) DEFAULT NULL,
  `people2_num` varchar(255) DEFAULT NULL,
  `people2_sign` varchar(255) DEFAULT NULL,
  `people2_signname` varchar(255) DEFAULT NULL,
  `people2_time` varchar(255) DEFAULT NULL,
  `people3_email` varchar(255) DEFAULT NULL,
  `people3_encrypt` text,
  `people3_name` varchar(255) DEFAULT NULL,
  `people3_num` varchar(255) DEFAULT NULL,
  `people3_sign` varchar(255) DEFAULT NULL,
  `people3_signname` varchar(255) DEFAULT NULL,
  `people3_time` varchar(255) DEFAULT NULL,
  `people_size` int NOT NULL,
  `plain_text` varchar(255) DEFAULT NULL,
  `result_hash` varchar(255) DEFAULT NULL,
  `ser_fileName` varchar(255) DEFAULT NULL,
  `sign1_xpos` int DEFAULT NULL,
  `sign1_ypos` int DEFAULT NULL,
  `sign2_xpos` int DEFAULT NULL,
  `sign2_ypos` int DEFAULT NULL,
  `sign3_xpos` int DEFAULT NULL,
  `sign3_ypos` int DEFAULT NULL,
  `sign_count` int NOT NULL,
  `uniquenum` varchar(255) DEFAULT NULL,
  `x` varchar(255) DEFAULT NULL,
  `y` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Article`
--

LOCK TABLES `Article` WRITE;
/*!40000 ALTER TABLE `Article` DISABLE KEYS */;
/*!40000 ALTER TABLE `Article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Copyright`
--

DROP TABLE IF EXISTS `Copyright`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Copyright` (
  `id` bigint NOT NULL,
  `createDate` datetime(6) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_size` bigint NOT NULL,
  `orig_name` varchar(255) DEFAULT NULL,
  `orig_papername` varchar(255) DEFAULT NULL,
  `papername` varchar(255) DEFAULT NULL,
  `people1_email` varchar(255) DEFAULT NULL,
  `people1_encrypt` text,
  `people1_name` varchar(255) DEFAULT NULL,
  `people1_num` varchar(255) DEFAULT NULL,
  `people1_sign` varchar(255) DEFAULT NULL,
  `people1_signname` varchar(255) DEFAULT NULL,
  `people1_time` varchar(255) DEFAULT NULL,
  `people_size` int NOT NULL,
  `plain_text` varchar(255) DEFAULT NULL,
  `result_hash` varchar(255) DEFAULT NULL,
  `ser_fileName` varchar(255) DEFAULT NULL,
  `sign1_xpos` int DEFAULT NULL,
  `sign1_ypos` int DEFAULT NULL,
  `sign_count` int NOT NULL,
  `uniquenum` varchar(255) DEFAULT NULL,
  `x` varchar(255) DEFAULT NULL,
  `y` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Copyright`
--

LOCK TABLES `Copyright` WRITE;
/*!40000 ALTER TABLE `Copyright` DISABLE KEYS */;
/*!40000 ALTER TABLE `Copyright` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomerCenter`
--

DROP TABLE IF EXISTS `CustomerCenter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CustomerCenter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admincomment` text,
  `createDate` datetime(6) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `hierachyUrl` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `secretcheck` varchar(255) DEFAULT NULL,
  `secretpassword` varchar(255) DEFAULT NULL,
  `server_filename` varchar(255) DEFAULT NULL,
  `textbody` text,
  `title` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerCenter`
--

LOCK TABLES `CustomerCenter` WRITE;
/*!40000 ALTER TABLE `CustomerCenter` DISABLE KEYS */;
/*!40000 ALTER TABLE `CustomerCenter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (4);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Post`
--

DROP TABLE IF EXISTS `Post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Post` (
  `id` bigint NOT NULL,
  `createDate` datetime(6) DEFAULT NULL,
  `hwpLink` varchar(255) DEFAULT NULL,
  `pdfLink` varchar(255) DEFAULT NULL,
  `postType` varchar(255) DEFAULT NULL,
  `serverhwp` varchar(255) DEFAULT NULL,
  `serverpdf` varchar(255) DEFAULT NULL,
  `specificType` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Post`
--

LOCK TABLES `Post` WRITE;
/*!40000 ALTER TABLE `Post` DISABLE KEYS */;
/*!40000 ALTER TABLE `Post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `email` varchar(255) NOT NULL,
  `authKey` varchar(255) DEFAULT NULL,
  `authStatus` varchar(255) DEFAULT NULL,
  `createDate` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `privateKey` text,
  `provider` varchar(255) DEFAULT NULL,
  `providerId` varchar(255) DEFAULT NULL,
  `publicKey` text,
  `realName` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `signCount` int NOT NULL,
  `signName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES ('user','026204','Y','2021-11-15 00:04:52.171000','$2a$10$ujak0M00h1wBy06BkauP3u1/.oTeRueA6WtXL9PCvSnp7/OYqMhxe','1122223333','MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJkmuIYompKKU7l2HrrUY+Mc8jMOO22LbLk4OUuDNr0aAA4/27AVSfxkt9qRYPa7/4p4eflnOM552nBHcgy5ncjte0c0wtOA67aPNKohEulTObv9tNL5vKIjihvgemmig//nNQcDX2V/ZW3LNkhcdxR6WL/InMTzjXkMQts3Rz27AgMBAAECgYAWq4Gi8j23NVyhVlfEJg5HHxDG9/NaCspkPGtWvyyKH2Aku9UkInKaNrp0UxK8UyXHdH/xqBLjlWv9NMYwFgXB7bfo8iBEx5kqX19jXCBGKVQXYLgRBqajb3TvmRZZVGxw3MiP5q7x9TXwXkYxmcuxAdTonK8tgh0PcEkNxFTQwQJBAO1PIFGnp3yoTWku1axB0WyuZ0o+m2+JUqoKzPNQax2PbebUk1uYuWWO37bvJSyeJLoTsZvHaHD7L0UkQ216F5sCQQClNrdkfGRBVDioXVENP93vMXUjXa6P1ITN5/B6vhrXPg5kuRnSFOzbAi/q4pJ4QE683icfnjrG9PE1BE7df6RhAkAHZc8RHJPZC/oVzFL24rpfk/Q+WUNJL9O2301STHB6ZG09o+TQFyu4YcLRlhTnlip47oI74/R6iSoJEr9TW1n/AkBEe7/dWWq9YcnykGvukBDQ+7FQrZ6UZEWAjKo0VuUNX93bhpbbbG0aA3gVZ0HLsuxwga25/37hxdrOXTDLxANBAkAvHu9qwKRzF44ta3ho+GXybHltuYAh2zwizCCj4Hmo+gMWMvuWNfcXKhWhc/vIVMtPVvTsRz9Rw3AxOouXoDx3',NULL,NULL,'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZJriGKJqSilO5dh661GPjHPIzDjtti2y5ODlLgza9GgAOP9uwFUn8ZLfakWD2u/+KeHn5ZzjOedpwR3IMuZ3I7XtHNMLTgOu2jzSqIRLpUzm7/bTS+byiI4ob4HppooP/5zUHA19lf2VtyzZIXHcUeli/yJzE8415DELbN0c9uwIDAQAB','user','ROLE_USER',0,'');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserSign`
--

DROP TABLE IF EXISTS `UserSign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserSign` (
  `SIGN_ID` bigint NOT NULL,
  `FILE_NAME` varchar(255) DEFAULT NULL,
  `IS_NFT` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SIGN_ID`),
  KEY `FK3w853f8fe7l67ca2u94i1xun` (`email`),
  CONSTRAINT `FK3w853f8fe7l67ca2u94i1xun` FOREIGN KEY (`email`) REFERENCES `User` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserSign`
--

LOCK TABLES `UserSign` WRITE;
/*!40000 ALTER TABLE `UserSign` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserSign` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-15 22:32:28
