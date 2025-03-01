-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: schooldb
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Popescu Anamaria','Strada Aleea Zorilor 6','popescu_anamaria@gmail.com',25),(2,'Moldovan Andreea','Strada Campiei 28','moldovan_andreea@gmail.com',28),(3,'Ionescu Stefan','Bulevardul Primaverii 14','ionescu_stefan@gmail.com',30),(4,'Popa Maria','Strada Trandafirilor 22','popa_maria@gmail.com',35),(5,'Dumitru Ion','Bulevardul Independentei 7','dumitru_ion@gmail.com',40),(6,'Georgescu Elena','Strada Libertatii 33','georgescu_elena@gmail.com',22),(7,'Stanciu Mihai','Aleea Bradului 10','stanciu_mihai@gmail.com',27),(8,'Florescu Ana','Strada Plopilor 15','florescu_ana@gmail.com',32),(9,'Constantinescu Maria','Bulevardul Victoriei 9','constantinescu_maria@gmail.com',29),(10,'Iacob Andrei','Strada Iasomiei 12','iacob_andrei@gmail.com',26),(11,'Alexandrescu Cristina','Aleea Mihai Viteazu 20','alexandrescu_cristina@gmail.com',31),(12,'Radulescu Ionut','Bulevardul Dacia 45','radulescu_ionut@gmail.com',24),(64,'Juravle Monica','Strada Sportivilor 4','monitehnic@srl.com',20),(79,'Kulcsar Noemi','Strada Aleea Zorilor 6','nomikulcsar@yahoo.com',20);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-14  5:41:55
