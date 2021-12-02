CREATE DATABASE  IF NOT EXISTS `baronarin` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `baronarin`;
-- MySQL dump 10.13  Distrib 8.0.22, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: baronarin
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
INSERT INTO `Article` VALUES (4,'2021-11-19 15:13:24.198000','/Users/yong/github/baronarin/input/1867de0e122041b9a4155f0c860f8d20.png',499643,'KakaoTalk_Photo_2021-11-02-18-53-58.png','test','4_test','dydwns1308@naver.com','JdyY6oukaPOT9wKludq4XzVkn6isQDXJqVqyV/dwJjTNd0DE3qGshWHR0y13Fq4y9fYFea13GWAYG2Gru8fBKLnp5O7Q/GFmtt/ncsHGQvZAhUQPZ7gMkkXBaXP6Xd1RhDvvnevNY91Vb0UK4hh5NgAZKoy7imtIMzp23aZdfZs=','user2',NULL,'1','dydwns1308@naver.com_sign.png','2021-11-19 15:16:57','serepa03@gmail.com','hqj2DlysH6tYx8DyrRizGWDq/4U4RH6lrqdAREKn/zoOQSJIziPnkiqEt8rB4iOCfywCv9gOt5QVRepiv7z7skFDhQq/cAYgLHpP9XvaCKTs3Wnmge6LoWyMQIxuMyV0vQPRkKC4Kzwi2nz4I4Ok98ku2ewRKmCbyeyvNEgrAAY=','test',NULL,'1','serepa03@gmail.com_sign.png','2021-11-19 15:14:45','',NULL,'',NULL,NULL,NULL,NULL,2,'계약서 원문','6dfa2acc2eb0f1b20833dc0cfc8cade2b595bd471112531ae11cdba23c977fa2','1867de0e122041b9a4155f0c860f8d20.png',223,109,303,295,0,0,2,'20211119151324198',NULL,NULL),(8,'2021-11-19 15:46:57.692000','/Users/yong/github/baronarin/input/e931070a738e48b48a0106c3bbd04f09.png',57528,'GDSC-Logo.png','test2','8_test2','serepa03@gmail.com',NULL,'test2',NULL,NULL,'serepa03@gmail.com_sign.png',NULL,'dydwns1308@naver.com','JdyY6oukaPOT9wKludq4XzVkn6isQDXJqVqyV/dwJjTNd0DE3qGshWHR0y13Fq4y9fYFea13GWAYG2Gru8fBKLnp5O7Q/GFmtt/ncsHGQvZAhUQPZ7gMkkXBaXP6Xd1RhDvvnevNY91Vb0UK4hh5NgAZKoy7imtIMzp23aZdfZs=','user2',NULL,'1','dydwns1308@naver.com_sign.png','2021-11-19 16:50:18','',NULL,'',NULL,NULL,NULL,NULL,2,'계약서 원문',NULL,'e931070a738e48b48a0106c3bbd04f09.png',105,160,460,171,0,0,1,'20211119154657692',NULL,NULL),(13,'2021-11-19 17:20:00.104000','/Users/yong/github/baronarin/input/2963febd88944e5abe3210cd421a43af.png',57528,'GDSC-Logo.png','test3','13_test3','ubermen5ch1308@gmail.com','WMr7pEV6ehxCDqjH64SChFNlujMiL9JdBhbnNzowsSIWhufaL7aOPHfOdWKRpCeQZPYBLOEzoCopDQ/Zr5ILFNvc/thX7LJR4MORlQgoecbHvOgZQoeePrLrMF7BLK+Uvkuk+VRciAIDmyHDWorSO8nT5AFgfCXgX45wq9oDgQM=','uber',NULL,'1','ubermen5ch1308@gmail.com_sign3.jpg','2021-11-19 17:20:59','dydwns1308@naver.com','JdyY6oukaPOT9wKludq4XzVkn6isQDXJqVqyV/dwJjTNd0DE3qGshWHR0y13Fq4y9fYFea13GWAYG2Gru8fBKLnp5O7Q/GFmtt/ncsHGQvZAhUQPZ7gMkkXBaXP6Xd1RhDvvnevNY91Vb0UK4hh5NgAZKoy7imtIMzp23aZdfZs=','user2',NULL,'1','dydwns1308@naver.com_sign.png','2021-11-19 17:20:31','',NULL,'',NULL,NULL,NULL,NULL,2,'계약서 원문','dd49623a0423e9bcd29c9b79a73798d0e66d09b3c656801f89a86611d3bfa70e','2963febd88944e5abe3210cd421a43af.png',229,221,472,220,0,0,2,'20211119172000104',NULL,NULL),(14,'2021-11-19 17:23:15.197000','/Users/yong/github/baronarin/input/d11906229ebc44768a6e980fb04812a5.png',499643,'KakaoTalk_Photo_2021-11-02-18-53-58.png','test4','14_test4','ubermen5ch1308@gmail.com','WMr7pEV6ehxCDqjH64SChFNlujMiL9JdBhbnNzowsSIWhufaL7aOPHfOdWKRpCeQZPYBLOEzoCopDQ/Zr5ILFNvc/thX7LJR4MORlQgoecbHvOgZQoeePrLrMF7BLK+Uvkuk+VRciAIDmyHDWorSO8nT5AFgfCXgX45wq9oDgQM=','uber',NULL,'1','ubermen5ch1308@gmail.com_sign3.jpg','2021-11-19 17:24:30','dydwns1308@naver.com','JdyY6oukaPOT9wKludq4XzVkn6isQDXJqVqyV/dwJjTNd0DE3qGshWHR0y13Fq4y9fYFea13GWAYG2Gru8fBKLnp5O7Q/GFmtt/ncsHGQvZAhUQPZ7gMkkXBaXP6Xd1RhDvvnevNY91Vb0UK4hh5NgAZKoy7imtIMzp23aZdfZs=','user2',NULL,'1','dydwns1308@naver.com_sign.png','2021-11-19 17:24:49','',NULL,'',NULL,NULL,NULL,NULL,2,'계약서 원문','0e32d8654057021644e4807d8cf089d460e2d6c1167c11b5b12b35cb0b4cbc09','d11906229ebc44768a6e980fb04812a5.png',278,322,508,320,0,0,2,'20211119172315197',NULL,NULL);
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
INSERT INTO `hibernate_sequence` VALUES (15);
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
INSERT INTO `User` VALUES ('darkbyte@naver.com','808119','N','2021-11-19 15:10:59.642000','$2a$10$db/K.MIe0URV7VJi377BUO1K9MLsznisLVFiQhwsv.n6bfNYetfU.','1234123412','MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIsLXwF+2PNrDqChE+AC6218MPZxt94sKnGtbLeRtuUBexrnT0oYMlNQwtG55gS5/hjhE/n6EGhbhizXhrwNqAq76IgHvTmyDltnhLzntuenk6TSHcM974zR/7wBnwX547UgYJGBZYCYJfXpdAzObDIuUPuKQ1brVSDkradnrKjBAgMBAAECgYBAWcvnikhoJ+UGLGZhZ5iD/FVAyZKaLYCbSfMl5jdqN0w9QE5u8VHhQ2BW5h1xlI7n/stbBwOu+KTZfgoqYHujgwnr9jEbZ3pUjULpzuj9qwk2vmEtnJtBk4umDXsea971AjAqoIC4GFRwCdlV9XGLTmSRqCK+1p+n0wGDf/SXXQJBAN8Wm++BHuU3ran6whgSTO6Sn276wSPc7muzItV6fKmN7AVpsDGINh9bh8DyYYHOs7pxHJdLcKDhp3u4cb3b/LsCQQCfjquvvGhgq81aOBecT7MOOczZhzoyghzEoi95fuxkvYZzpxIxJ/CEAwB9/obbN3zZepIyQhKF2V5/PLX3qrazAkEAo5YXqIu9VQX8Ea3wT+hzg2n/AUldAWhLaKxGOdaEvWx1iw6SBfBFB799j43s1Fv3TRsOhR7rqv1ta/q3ZSqAawJAEjuacOWFw+499B5OzGTrICgIuZ7TTqxBn1KtPCP93XEu6Q5hbM6UlHFHYB5yyKApmyx/glC8jyEMxFtqq9eNMwJAF92H6QTG6fRCDMyIWO8WHsdqozUmysW7KjKdd6QLEcm0xXlqZLkSzYYFcNR6ZK/rwq1xUgFDJVsAwvJvUdNk1Q==',NULL,NULL,'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLC18Bftjzaw6goRPgAuttfDD2cbfeLCpxrWy3kbblAXsa509KGDJTUMLRueYEuf4Y4RP5+hBoW4Ys14a8DagKu+iIB705sg5bZ4S857bnp5Ok0h3DPe+M0f+8AZ8F+eO1IGCRgWWAmCX16XQMzmwyLlD7ikNW61Ug5K2nZ6yowQIDAQAB','user2','ROLE_USER',0,NULL),('dydwns1308@naver.com','077805','Y','2021-11-19 15:11:41.385000','$2a$10$FkXLtOjB9Go1NnAfn8nHA.22htXP2nMuDkS7mBQTJa8s3W1L6lgxO','1234123411','MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJdmAE+c/1miG1zO8vaS/BYisU+98IgPQTxlz4Nan5UYN8brrJwMQYAikxCyWcqxR8+xW5+zNOKIJQ3tUoUCDhTgQETE6zAEvjshhY7RSLQRYpxMYknNCAY24Zk11QTi2rTbY+wax5R2rb51GffUEOMickYgqb0msCXMStY5DiSzAgMBAAECgYB14OpvEmtTC7/IRC2yRIFYbsylrNrh5RbDAvZObLoB6ShMjmUH3xcEwGbHQkZdZijmkk4niu2rT5uiO1zbyNzSt/lX16zGg0j3ByBDhEz7SonNs3/kaLGZn6fuwynAQDxggFhkQfnNysQr/ItXTzEZ/wlovL6AN6xl7NlKy5eR6QJBAOYGQAOOvjzeWo+4ZA5ZRL7zE8yw34Dhe89AU2nSG/NQpZrioF6svGXGZvGiZD+HH6bfx+FGtsrF/V8ytb0xi+cCQQCofsMDWdrSBDLJcWddCJJjTVyNxctnE5R6QU+x4/pzL5w6zmxl9+/txnEhq4XSMcU1NRvW3hIfd6SawJ99EadVAkEA5LjmQiNXypKWnj4NT684Cl8OGwTs2Oxc/fHKf3iqzkAsy1v5dupv0wt7JV4CLKEhuf7ec4SrEenZTSXRTLTNlQJBAJyWGVN+6N2uKyiMLFwIQErqtS3H+IyVZLHtCkwuqyzqvQzlk1V8Akx909tPlLDPsg8NiQ65GCyIOr/abR2FD3UCQCxAb+IsMQbLwPP70GaZY8tGb/BmeWpDvmagxkNDP0iOl1f5t1NwNrE4UICiJF7HMluTlurGqChMvEm67YLQwXg=',NULL,NULL,'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXZgBPnP9ZohtczvL2kvwWIrFPvfCID0E8Zc+DWp+VGDfG66ycDEGAIpMQslnKsUfPsVufszTiiCUN7VKFAg4U4EBExOswBL47IYWO0Ui0EWKcTGJJzQgGNuGZNdUE4tq022PsGseUdq2+dRn31BDjInJGIKm9JrAlzErWOQ4kswIDAQAB','user2','ROLE_USER',3,'dydwns1308@naver.com_sign.png'),('serepa03@gmail.com','719369','Y',NULL,'$2a$10$tNBHFEEBWbMwqCHzkQ8ZD.FrqzcEvXTXPJ11v.zcIgBdvfKjhrxn6','2233441123','MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAKe9XhhSRzMdVT98unclfCZxungnDwp039unRsOeuWDL2d76aMc0Dmnmn8zgq8KyOmQ3a8fAxllYkIcz0KzsJ2ZQemmsSiegqhkiKMU+OC0XeCHrRER21Z8/q6YKV8wRekxlkw2uER5rDVHvJbdPJOZfZ1CjQfdRFwFpVKVFH3//AgMBAAECgYA3feVQJIAbf9vA6hGB8yu0SDGJKFrIecvJDfjSJINta2MDalIZfLY8AKjjDXylJ3M7zj3g0nyL+EmwAMtLOcD6hAqoJ90GoMGsJ1Gc9uXNEMipXs/dflYOMvQe7h3ruYOgGrdjys4NVDttQOMy+rlJWe7Qe4BxCtlFg6fsQRTkiQJBAPX1HOrVpspIZC02VQg63vdWD8rlsEqVwqa1QqiONEZhnk/SWRG7nX0SwtfZn3v6lx/KBsx1rcPCkiKw2Q1B/WUCQQCulq2hdFUIraqB2XxOwLKEtpWrRV2zvycgdl8zBPmgMgMDR6X0hs97ZqPGL4g42VUULyd/t9GARFYvif/OMpOTAkEAlcL/LZqUKX10yfbYRd+a3h2gFhpPgsq3sEyUXw/fkq9j9xSMuXh0x7CBxW60ZohKNsgL5bkN6jHyS39FWoZA5QJBAKodo924Lssf/Q49ToSl+syZ5EhYdK8aSRO2g5iA9oMDeDAP9kLHwQ+quVX5JrDOA7qBH+mwwPBMpfX4b0sJuQUCQQDHhpiZk8zd+3asUbwQy1xn9BgAIHPBo+g2li27Bem3tlJBv2ohxgM011+mRmPZDsXUsjgfF6Ju8HSlX+eQ77me',NULL,NULL,'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnvV4YUkczHVU/fLp3JXwmcbp4Jw8KdN/bp0bDnrlgy9ne+mjHNA5p5p/M4KvCsjpkN2vHwMZZWJCHM9Cs7CdmUHpprEonoKoZIijFPjgtF3gh60REdtWfP6umClfMEXpMZZMNrhEeaw1R7yW3TyTmX2dQo0H3URcBaVSlRR9//wIDAQAB','test','ROLE_USER',0,'serepa03@gmail.com_sign.png'),('ubermen5ch1308@gmail.com','519776','Y',NULL,'$2a$10$Y0PeQYcYC4iUVcC0P5wbieetSv2hj9OfMHwbNe5hUEOaFD2Gqg6iy','1122223333','MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKecO57WXdPitJviXiOAEYsQbaUt9VUeoaEPb+6TVxhngdH5Q96OufbvUqam1p3nLU1YJGepuoe4cGSSFzoRUZ+l+r+sR2tiLXFI8tVjjiNSJi5Y5jR1Jm/KRRLw404V1rLH9FjlFvt3OGAozmLijHhTD7O0ZsQrsi664ZpWh+83AgMBAAECgYBdqmFnEkS89CQaORaCoD8th7C+bkB7oPKSHx5eJNi3OqDBxpq29OvXo0Z8+x9UfqxCXM97Xs5uqhOlID76pFMIDAO9Kt/ky3iyPdYFniIEvpf4uHaJlCfFN6K0IfUrjK7+YuTTjQHGCh79ipOWIuoUBDhm5mfHSl1iv8fIuRXxAQJBAOSg375cfSiQ1wpXW5nw5gHej0Q5ljw5Nh5C4eVlwpTQmkqfbgFd+Puv0/9TWnTybRzuRFJL9xjbBqKS6eFayjkCQQC7rT4bkZLF4CtdwLGtmd09SkEh/QQlHybjFwX0RlBT/aFeGynXm1nbkXDrEQEc9tJotPwyeG02X2WgV5O1fkTvAkEAsuqujhNlItTOZFpe5gaeiQH5jgOe7Xw8PMxMabAL6VeiFxnTBaDb02MS/XHmjTSWbRiNQPYP5lSpSZq9Cs15CQJAIjlKXR6jxD/HyLXt8LlhNd8Hl5obGmXe4oR9SnlUBqz2s0GC1NV4gmi4TbVxHYYJAp0WbGG3kLW+w4mU3wDiswJAEuvEBqAUM7Um+oCrHVCsU2VirTCC3ZS71vnCEFrxh93y8vr8L3N0zKdbqp2ENTCgYodV9u+zpZes7ew1Mm0iBA==',NULL,NULL,'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnnDue1l3T4rSb4l4jgBGLEG2lLfVVHqGhD2/uk1cYZ4HR+UPejrn271Kmptad5y1NWCRnqbqHuHBkkhc6EVGfpfq/rEdrYi1xSPLVY44jUiYuWOY0dSZvykUS8ONOFdayx/RY5Rb7dzhgKM5i4ox4Uw+ztGbEK7IuuuGaVofvNwIDAQAB','uber','ROLE_USER',4,'ubermen5ch1308@gmail.com_sign3.jpg');
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
INSERT INTO `UserSign` VALUES (5,'dydwns1308@naver.com_sign0.jpg','T','dydwns1308@naver.com'),(6,'dydwns1308@naver.com_sign1.jpg','F','dydwns1308@naver.com'),(7,'dydwns1308@naver.com_sign2.jpg','F','dydwns1308@naver.com'),(9,'ubermen5ch1308@gmail.com_sign0.jpg','T','ubermen5ch1308@gmail.com'),(10,'ubermen5ch1308@gmail.com_sign1.jpg','T','ubermen5ch1308@gmail.com'),(11,'ubermen5ch1308@gmail.com_sign2.jpg','T','ubermen5ch1308@gmail.com'),(12,'ubermen5ch1308@gmail.com_sign3.jpg','T','ubermen5ch1308@gmail.com');
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

-- Dump completed on 2021-12-02 12:06:12
