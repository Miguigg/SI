-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pruebas_si
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `accidente`
--

DROP TABLE IF EXISTS `accidente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accidente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `costeEstimado` double DEFAULT NULL,
  `descripcionGravedad` varchar(255) DEFAULT NULL,
  `fechaAccidente` date DEFAULT NULL,
  `periodo` varchar(255) DEFAULT NULL,
  `parteSiniestro_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3igg9n5fq6l2rj77c0awyfaak` (`parteSiniestro_id`),
  CONSTRAINT `FK3u358wjyp51saxba0rw5uia1m` FOREIGN KEY (`parteSiniestro_id`) REFERENCES `partesiniestro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accidente`
--

LOCK TABLES `accidente` WRITE;
/*!40000 ALTER TABLE `accidente` DISABLE KEYS */;
INSERT INTO `accidente` VALUES (1,1300,'muy grave','2025-09-11','AM',1);
/*!40000 ALTER TABLE `accidente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accidente_definiciontipo`
--

DROP TABLE IF EXISTS `accidente_definiciontipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accidente_definiciontipo` (
  `Accidente_id` bigint NOT NULL,
  `definicionTipo_id` bigint NOT NULL,
  KEY `FKj7rprpf8ljf970n9tx8dlkd20` (`definicionTipo_id`),
  KEY `FKedjlybif1fdq6dbilxodr656m` (`Accidente_id`),
  CONSTRAINT `FKedjlybif1fdq6dbilxodr656m` FOREIGN KEY (`Accidente_id`) REFERENCES `accidente` (`id`),
  CONSTRAINT `FKj7rprpf8ljf970n9tx8dlkd20` FOREIGN KEY (`definicionTipo_id`) REFERENCES `definiciontipo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accidente_definiciontipo`
--

LOCK TABLES `accidente_definiciontipo` WRITE;
/*!40000 ALTER TABLE `accidente_definiciontipo` DISABLE KEYS */;
INSERT INTO `accidente_definiciontipo` VALUES (1,1);
/*!40000 ALTER TABLE `accidente_definiciontipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `condiciones`
--

DROP TABLE IF EXISTS `condiciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `condiciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcionCondicion` varchar(255) DEFAULT NULL,
  `nombreCondicion` varchar(255) DEFAULT NULL,
  `tipoCondicion` enum('CONDICIONES_LUMINICAS','CONDICIONES_TEMPORALES','CONDICIONES_TERRENO','NO_DEFINIDO') DEFAULT NULL,
  `AccidenteId` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtl99ck2ksfcuf9ixwg9dnv4oj` (`AccidenteId`),
  CONSTRAINT `FKtl99ck2ksfcuf9ixwg9dnv4oj` FOREIGN KEY (`AccidenteId`) REFERENCES `accidente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `condiciones`
--

LOCK TABLES `condiciones` WRITE;
/*!40000 ALTER TABLE `condiciones` DISABLE KEYS */;
INSERT INTO `condiciones` VALUES (1,'Salida de la calzada','Mal estado de la carretera','CONDICIONES_LUMINICAS',1);
/*!40000 ALTER TABLE `condiciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conductor`
--

DROP TABLE IF EXISTS `conductor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conductor` (
  `DNI` varchar(255) NOT NULL,
  `anhoExp` int DEFAULT NULL,
  `nivelEducativo` enum('GRADO','MEDIO','SIN_ESTABLECER','SIN_ESTUDIOS','SUPERIOR') DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT NULL,
  `ParteId` bigint DEFAULT NULL,
  PRIMARY KEY (`DNI`),
  KEY `FKhu3tn0djswcy0b72s7dcfkqa5` (`ParteId`),
  CONSTRAINT `FKhu3tn0djswcy0b72s7dcfkqa5` FOREIGN KEY (`ParteId`) REFERENCES `partesiniestro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conductor`
--

LOCK TABLES `conductor` WRITE;
/*!40000 ALTER TABLE `conductor` DISABLE KEYS */;
INSERT INTO `conductor` VALUES ('19706812A',1,'MEDIO','Pepe','Hombre',1);
/*!40000 ALTER TABLE `conductor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `definiciontipo`
--

DROP TABLE IF EXISTS `definiciontipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `definiciontipo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `tipoAccidente` enum('ALCANCE','ATROPELLO','CHOQUE_FRONTAL','CHOQUE_LATERAL','CHOQUE_MULTIPLE','CHOQUE_TRASERO','INCENDIO','NO_DEFINIDO','OTRO','RASPADO','SALIDA_CARRETERA','VUELCO') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `definiciontipo`
--

LOCK TABLES `definiciontipo` WRITE;
/*!40000 ALTER TABLE `definiciontipo` DISABLE KEYS */;
INSERT INTO `definiciontipo` VALUES (1,'Se ha matado Pepe','ALCANCE');
/*!40000 ALTER TABLE `definiciontipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partesiniestro`
--

DROP TABLE IF EXISTS `partesiniestro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partesiniestro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `horaFirmaParte` varchar(255) DEFAULT NULL,
  `numPasajeros` int DEFAULT NULL,
  `numVehiculos` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partesiniestro`
--

LOCK TABLES `partesiniestro` WRITE;
/*!40000 ALTER TABLE `partesiniestro` DISABLE KEYS */;
INSERT INTO `partesiniestro` VALUES (1,'12:00',3,2);
/*!40000 ALTER TABLE `partesiniestro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `licencePlate` varchar(255) NOT NULL,
  `defectoVehiculo` varchar(255) DEFAULT NULL,
  `fechaMatriculacion` date DEFAULT NULL,
  `tipoVehiculo` enum('AUTOBUS','BUS','CAMION','CARABANA','CICLOMOTOR','FURGON','OTRO','QUAD','SIN_DEFINIR','TURISMO') DEFAULT NULL,
  `ParteId` bigint DEFAULT NULL,
  PRIMARY KEY (`licencePlate`),
  KEY `FKokh86eeuc4othe7819yjkbq3q` (`ParteId`),
  CONSTRAINT `FKokh86eeuc4othe7819yjkbq3q` FOREIGN KEY (`ParteId`) REFERENCES `partesiniestro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES ('1222QCW','Frenos en mal estado','2025-09-11','BUS',1),('1332QCW','Neumaticos en mal estado','2025-09-11','CARABANA',1);
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-11 11:38:37
