-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: relsis_db
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `client_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `contactPIB` varchar(45) NOT NULL,
  `codeERPOU` varchar(45) DEFAULT NULL,
  `codeTaxpayer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Компанія \"StarLight\"','122-12-12','Беляева Аліна Петрівна','121212121212','121212'),(2,'Южноукраїнська АЕС','133-13-13','Петрусь Петро Максимович','131313131313','131313'),(3,'Фірма \"Електрон\"','144-14-14','Невзоров Ростислав Ігорович','141414141414','141414');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `component` (
  `component_id` int(11) NOT NULL,
  `type` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `technical_info` varchar(100) DEFAULT NULL,
  `price` int(11) NOT NULL,
  PRIMARY KEY (`component_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` VALUES (1,'резистор','Резистор10кОМ','ййййййййййййййййййййййййййййййййййййй',10),(2,'резистор','Резистор20кОм','фффффффффффффффффф',20),(3,'резистор','Резистор30кОм','дтмшщкітамкдтктквутпму',30),(4,'індикатор','Індикатор1000','щшкокомаоупщіжтпкпоукжітдпокдепюьіжпьекжд',8),(5,'діод','Діод(ART-21)','ccccccccccccc',12),(6,'діод','ДіодA','oijferjfrjf',10),(7,'індикатор','ДіодB','opewekfme;if',8),(8,'мікросхема','МікросхемаLight','eswdfefwf',15),(9,'індикатор','МікросхемаMedium','cknscekfwkwiefj',20),(10,'корпус','КорпусМаксимум','ссссссссссссссссс',100),(11,'індикатор','КорпусСередній','лстлагіуцазщоушца',50);
/*!40000 ALTER TABLE `component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component_device`
--

DROP TABLE IF EXISTS `component_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `component_device` (
  `component_id` int(11) NOT NULL,
  `device_id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`component_id`,`device_id`),
  KEY `fk_Component_Device_Component_idx` (`component_id`),
  KEY `fk_Component_Device_Device1_idx` (`device_id`),
  CONSTRAINT `fk_Component_Device_Component` FOREIGN KEY (`component_id`) REFERENCES `component` (`component_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Component_Device_Device1` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component_device`
--

LOCK TABLES `component_device` WRITE;
/*!40000 ALTER TABLE `component_device` DISABLE KEYS */;
INSERT INTO `component_device` VALUES (1,1,30),(1,6,40),(1,8,35),(2,4,30),(3,2,40),(3,3,50),(3,7,10),(3,8,20),(3,9,20),(4,3,20),(4,5,10),(4,6,35),(5,3,100),(5,5,100),(6,1,40),(6,4,50),(7,2,50),(7,6,30),(7,7,30),(7,8,20),(8,1,15),(8,5,10),(8,6,25),(9,3,30),(9,5,36),(10,3,1),(10,5,1),(11,1,1),(11,4,1),(11,6,1);
/*!40000 ALTER TABLE `component_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery` (
  `delivery_id` int(11) NOT NULL,
  `delivery_name` varchar(80) NOT NULL,
  `provider_id` int(11) NOT NULL,
  `startdate` date NOT NULL,
  `paid` tinyint(4) NOT NULL,
  `shipped` tinyint(4) NOT NULL,
  `enddate` date DEFAULT NULL,
  `sum_cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`delivery_id`,`provider_id`),
  KEY `fk_Delivery_Provider1_idx` (`provider_id`),
  CONSTRAINT `fk_Delivery_Provider1` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`provider_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (1,'Замовлення постачальнику Фірма \"Діод3000\" №1',1,'2013-12-12',1,1,'2018-04-01',47000),(2,'Замовлення постачальнику Фірма \"Діод3000\" №2',1,'2014-12-12',0,0,NULL,30500);
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_component`
--

DROP TABLE IF EXISTS `delivery_component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_component` (
  `delivery_id` int(11) NOT NULL,
  `component_id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`delivery_id`,`component_id`),
  KEY `fk_Delivery_Component_Delivery1_idx` (`delivery_id`),
  KEY `fk_Delivery_Component_Component1_idx` (`component_id`),
  CONSTRAINT `fk_Delivery_Component_Component1` FOREIGN KEY (`component_id`) REFERENCES `component` (`component_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Delivery_Component_Delivery1` FOREIGN KEY (`delivery_id`) REFERENCES `delivery` (`delivery_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_component`
--

LOCK TABLES `delivery_component` WRITE;
/*!40000 ALTER TABLE `delivery_component` DISABLE KEYS */;
INSERT INTO `delivery_component` VALUES (1,1,700),(1,3,1000),(1,10,100),(2,1,900),(2,6,700),(2,8,300),(2,10,100);
/*!40000 ALTER TABLE `delivery_component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `device_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `supply_voltage` varchar(45) DEFAULT NULL,
  `border_regulation_time` varchar(45) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `work_price` int(11) NOT NULL,
  `sum_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`device_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,'РелеA-234','220','1-100',80,'2006-11-25',600,2362),(2,'РелеB-110','100','10-80',78,'2001-11-11',400,3000),(3,'Реле \"UltraRele\"','220','10-100',95,'2012-12-12',20000,35340),(4,'РелеZ-78','220','1-50',83,'2009-11-15',2000,4725),(5,'РелеY-222','100','40-100',77,'2006-06-06',1200,5175),(6,'РелеG-12','360','1-90',67,'2002-09-06',900,3367),(7,'Реле1001-TK','220','10-100',77,'2006-08-21',800,2010),(8,'ReleABC-31','100','10-100',56,'2008-08-08',1200,3465),(9,'RELE_TEST!)))','2200','1-10000',100,'2078-12-12',2000,3900);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_device`
--

DROP TABLE IF EXISTS `order_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_device` (
  `device_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  PRIMARY KEY (`device_id`,`order_id`),
  KEY `fk_Order_Device_Device1_idx` (`device_id`),
  KEY `fk_Order_Device_Order1_idx` (`order_id`),
  CONSTRAINT `fk_Order_Device_Device1` FOREIGN KEY (`device_id`) REFERENCES `device` (`device_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Device_Order1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_device`
--

LOCK TABLES `order_device` WRITE;
/*!40000 ALTER TABLE `order_device` DISABLE KEYS */;
INSERT INTO `order_device` VALUES (1,1,20),(1,2,20),(1,3,40),(2,1,30),(3,1,5);
/*!40000 ALTER TABLE `order_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL,
  `order_name` varchar(80) NOT NULL,
  `startdate` date NOT NULL,
  `paid` tinyint(4) NOT NULL,
  `shipped` tinyint(4) NOT NULL,
  `enddate` date DEFAULT NULL,
  `sum_cost` int(11) DEFAULT '0',
  PRIMARY KEY (`order_id`,`client_id`),
  KEY `fk_Order_Client1_idx` (`client_id`),
  CONSTRAINT `fk_Order_Client1` FOREIGN KEY (`client_id`) REFERENCES `client` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,'Замовлення клієнта Компанія \"StarLight\" №1','2015-12-12',1,1,'2018-04-01',313940),(2,1,'Замовлення клієнта Компанія \"StarLight\" №2','2017-01-01',0,0,NULL,47240),(3,2,'Замовлення клієнта Южноукраїнська АЕС №1','2015-01-01',0,0,NULL,94480),(4,1,'Замовлення клієнта Компанія \"StarLight\" №3','2018-01-04',0,0,NULL,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider` (
  `provider_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `codeERPOU` varchar(45) DEFAULT NULL,
  `codeTaxpayer` varchar(45) DEFAULT NULL,
  `contactPIB` varchar(45) NOT NULL,
  `specialization` varchar(90) DEFAULT NULL,
  PRIMARY KEY (`provider_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
INSERT INTO `provider` VALUES (1,'Фірма \"Діод3000\"','300-30-30','303030303030','303030','Валуев Андрій Іванович','діод, '),(2,'Завод \"IndicatorDiodCorpus\"','400-40-40','404040404040','404040','Дмитрієв Дмитро Валерійович','діод, індикатор, корпус, '),(3,'Компанія \"UltraScheme\"','500-50-50','505050505050','505050','Костомар Ірина Філіповна','мікросхема, діод, ');
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider_specialization`
--

DROP TABLE IF EXISTS `provider_specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provider_specialization` (
  `specialization_name` varchar(60) NOT NULL,
  PRIMARY KEY (`specialization_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider_specialization`
--

LOCK TABLES `provider_specialization` WRITE;
/*!40000 ALTER TABLE `provider_specialization` DISABLE KEYS */;
INSERT INTO `provider_specialization` VALUES ('індикатор'),('індуктивність'),('діод'),('контакти'),('корпус'),('мікросхема'),('резистор');
/*!40000 ALTER TABLE `provider_specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Петров','Іван','p','p','constructor'),(2,'Федотов','Максим','f','f','constructor'),(3,'Директор','Директорович','d','d','director'),(4,'Розін','Віталій','r','r','worker_of_sales_department'),(5,'Адмін','Адмінович','','','admin'),(6,'Захаренко','Аркадій','z','z','worker_of_delivery_department');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-07 13:51:05
