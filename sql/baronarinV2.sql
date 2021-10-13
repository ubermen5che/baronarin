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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomerCenter`
--

LOCK TABLES `CustomerCenter` WRITE;
/*!40000 ALTER TABLE `CustomerCenter` DISABLE KEYS */;
INSERT INTO `CustomerCenter` VALUES (1,NULL,'2021-07-27 00:16:10.222000',NULL,NULL,'심민기',NULL,'',NULL,'<p>현재 시각 기준 서버 상태 이상무</p>','서버 정상 알림','minkisim@naver.com'),(2,'답변테스트','2021-07-28 00:30:15.367000',NULL,NULL,'심민기',NULL,'',NULL,'<p><br></p>','서버 정상 알림','minkisim@naver.com');
/*!40000 ALTER TABLE `CustomerCenter` ENABLE KEYS */;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `createDate` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `privateKey` text,
  `provider` varchar(255) DEFAULT NULL,
  `providerId` varchar(255) DEFAULT NULL,
  `publicKey` text,
  `realname` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `signname` varchar(255) DEFAULT NULL,
  `authKey` varchar(255) DEFAULT NULL,
  `authStatus` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (21,'2021-10-13 18:22:17.700000','$2a$10$pKlyZYz06YanVihRsku3GOjTCRJ3tJtlOKzP/KndKMu0nujSVSWH2','MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJm54aBohPY1BPralsInPSh6oQwfyU6UT0hTGR/2zfDAIAPHQ7jsglGd+qXeiBc7lmxo0O9txsm7eWLZULn9d4uDemYmrjiLvupmTk27I0bld8zVvfC2RKzR3OtO4ulD0b4+SAuIXoVhgqgXvYuygBWRQKHxm8EiszcF/2Dnu5RjAgMBAAECgYATOsNUk35+Zed3kbxmEQ5/b8eRgrBl2fuI6owsCW19fisDDfwUt9EdcIuYEBwp+9Q3P21TZgIF3iOzD8gfW+uGIGIPWJFjtaCLxJQZ1IRNxqFJ+DhEKqqBId/e7vsSuj9h0cpUxa7CUJt1g76NSo8vS1XsGHDrV5l+CrNtW6Co8QJBANdSVNc8kyKgxED9dWB92zjS1WCtX/NgYSmElz6jcC+1MNCiDxC5AGnKz8i+OtamQNAuFanPb+ss/Auu7xLx7Q0CQQC2xJYhhF+uDy3K4tm1c1K52fi4wfxnQBwOPTKvsYYHHhyZbJK7XJKqOC9FEKVTH6jVJQ1csEg1EAQDEwzfR4svAkEAsN/t13zn9JsRe714zRG1oiD2rNrepeK3Dc7gGtFRF6+EcO2e6ILABydVtjYlySt6tEgW/Y3sBsjGJqbFts9KhQJBAI3JcJbL9NY5nFLbLf4gWzsnKFdc1ozbJWi+fUXjuJBpAdLccfC8XmODdthjk34q/3VrazRO5ZWUuSgRbVkCTa0CQCd7tUT0T1kK/iE2k6GrWRI3Q6osO0hFXXr8Yi5mMgPzRGsDNazHZI19/ym9eefUDpLatXTk/QCKNNYLAlqtk9c=',NULL,NULL,'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZueGgaIT2NQT62pbCJz0oeqEMH8lOlE9IUxkf9s3wwCADx0O47IJRnfql3ogXO5ZsaNDvbcbJu3li2VC5/XeLg3pmJq44i77qZk5NuyNG5XfM1b3wtkSs0dzrTuLpQ9G+PkgLiF6FYYKoF72LsoAVkUCh8ZvBIrM3Bf9g57uUYwIDAQAB','admin','ROLE_ADMIN',NULL,'664293','Y','admin',NULL),(22,'2021-10-13 18:23:47.298000','$2a$10$uV1doS6sWubySABSdFelGuRjj1GqA6prRXI89BHsddmjlKPjB/qxS','MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALmaBi7VngMMHzFYD+LC0dBAwIwQuZuHX/s2EdJSvQoDW+Gg1tS+IMun2sNQUyOcu1hNpJY08y/G8BPJAeKb2/EHlbTpcDJuysbtkwM4PP9WUSNSvtPDTatts19PKxEe/5Bf7zqtCoXRFWkB32mcUHg0jMnGDhtXgcG6nCCsREnTAgMBAAECgYEAhGqcQKvNWTU7x/OhGdKO6LpZ5VRFZ+4Uj2DLJDg9gLhWxEoBG7UuJr5x+GkFd+HpU53JcBUE77BKNoNxYeB68GsAgJ+t76cFeg6qklb5+ONaUg0+d4qlzDD2HP28kyM6BwC6v2KWQb6bvhTklTuAaylERjiCx9ktolxC9bAoonECQQDp2TS72SvzdEjzFBRqGufqWHDdxgBgj+V6Ob4hleS7TR1pytYU64H4duDZVhmmE0KyrNuOmmxIMbnmtmbHb5CrAkEAyy7W748tezSsAGUCFIDNAJCihAliNuUMERU3WxggvrWCJpqRNYhe8R+LMYzdcaI6DERQaftSh9QG9pYA1ZS7eQJARLHd3+DoHFhopZ+cb57/ycO5RCzKJ5Od4c3coJkb3DnHDjDbusGEKK0jWDUGy9aUnVBSHw4WCQ8ygSfMAYIwiQJAa3vKzg2y5KNXfhnxAMGmMO9SWYDRSPLf8EWF9BWOdPR19k/8ExKiDvI94yd+mbdU99pHo9kErr/2MwnlRRcaGQJBAM4O7BtmVtX/413FX6m2/atiXSC7pkGnrTYCiwBTYeP4lKyuucocAgGmvOvk1SmiFauIXfgdIu5x3JFiTxwptmY=',NULL,NULL,'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5mgYu1Z4DDB8xWA/iwtHQQMCMELmbh1/7NhHSUr0KA1vhoNbUviDLp9rDUFMjnLtYTaSWNPMvxvATyQHim9vxB5W06XAybsrG7ZMDODz/VlEjUr7Tw02rbbNfTysRHv+QX+86rQqF0RVpAd9pnFB4NIzJxg4bV4HBupwgrERJ0wIDAQAB','user','ROLE_USER',NULL,'204939','Y','user',NULL);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-13 18:25:37
