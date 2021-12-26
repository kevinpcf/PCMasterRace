DROP DATABASE IF EXISTS PCMasterRace;
CREATE DATABASE PCMasterRace;
USE PCMasterRace;

/*Tabella dei prodotti*/
CREATE TABLE prodotti (	
  codice int primary key AUTO_INCREMENT,
  nome char(50)not null,
  descrizione char(100),
  categoria char(20) not null,
  prezzo double default 0.00,
  quantita int default 0,
  IVA int default 22,
  immagine varchar(512) not null,
  CHECK (quantita > -1)
);

/*Tabella degli utenti*/
CREATE TABLE utenti (
username char(50) primary key,
password char(128) not null,
nome char(50) not null,
cognome char(50) not null,
admin boolean not null default false
);

CREATE TABLE indirizzi (
codice int primary key AUTO_INCREMENT,
utente char(50) not null,
via char(30) not null,
civico char(5) not null,
CAP char(5) not null,
citta char(30) not null,
provincia char(30) not null,
FOREIGN KEY (utente) REFERENCES utenti(username)
ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE pagamenti (
codice int primary key AUTO_INCREMENT,
utente char(50) not null,
numero char(16) not null,
CVV int not null,
scadenza char(12) not null,
FOREIGN KEY (utente) REFERENCES utenti(username)
ON UPDATE CASCADE ON DELETE CASCADE
);

/*Tabella degli ordini */
CREATE TABLE ordini (
id int primary key AUTO_INCREMENT,
username char(50) not null,
codice int not null,
data date not null,
quantita int not null default 1,
prezzo double not null,
codiceIndirizzo int not null,
codicePagamento int not null,
FOREIGN KEY (username) REFERENCES utenti(username)
ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (codice) REFERENCES prodotti(codice)
ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (codiceIndirizzo) REFERENCES indirizzi(codice)
ON UPDATE CASCADE ON DELETE CASCADE,
FOREIGN KEY (codicePagamento) REFERENCES pagamenti(codice)
ON UPDATE CASCADE ON DELETE CASCADE
);

/*Monitor*/
INSERT INTO prodotti values (1, "Lenovo G27-20 Monitor" , "27' Full HD, 1ms, 144Hz", "Monitor",299.00, 5, 22, "https://asset.mediaw.it/wcsstore/MMCatalogAssetStore/asset/images/15/19/151909.jpg");
INSERT INTO prodotti values (2, "Samsung Monitor F27T35","24' Full HD, 75Hz, 5 ms", "Monitor",209.00, 7, 22, "https://images.samsung.com/is/image/samsung/it-led-sf350-ls27f350fhuxen-001-front-black?$720_576_PNG$");
INSERT INTO prodotti values (3, "Acer Nitro QG271bii", "27' Full HD, 75 Hz, 1 ms", "Monitor",179.90, 6, 22, "https://images-na.ssl-images-amazon.com/images/I/7148HpcgXML._AC_SX450_.jpg");

/*Processori*/
INSERT INTO prodotti values (4, "Intel Core i7-10700K","8 core 5,1 GHz 125W", "CPU",349.99, 15, 22, "https://images-na.ssl-images-amazon.com/images/I/71P3chRzgNL._AC_SL1500_.jpg");
INSERT INTO prodotti values (5, "Intel Core i9-9900K", "8 core 3,6 GHz", "CPU",330.00, 4, 22, "https://images-na.ssl-images-amazon.com/images/I/61qUfPKfqJL._AC_SY450_.jpg");
INSERT INTO prodotti values (6, "AMD Ryzen 5 5600X", "6 core 4,6 GHz", "CPU",354.90, 8, 22, "https://images-na.ssl-images-amazon.com/images/I/61vGQNUEsGL._AC_SX466_.jpg");

/*Schede Video*/
INSERT INTO prodotti values (7, "MSI GeForce GTX 1080 ", "11GB PCIE 3.0 GDDR5X", "GPU",799.00, 1, 22, "https://asset.msi.com/resize/image/global/product/product_3_20171012153630_59df1b7ecd614.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png");
INSERT INTO prodotti values (8, "NVIDIA GTX 1050", "GDDR5 4GB PCIE 3.0", "GPU",379.00, 2, 22, "https://images-na.ssl-images-amazon.com/images/I/810mHUuMxuL._AC_SL1500_.jpg");
INSERT INTO prodotti values (9, "MSI GeForce RTX 2080", "11GB GDDR6 PCIEx16", "GPU",1099.00, 1, 22, "https://images-na.ssl-images-amazon.com/images/I/61z6tEdcO8L._AC_SX569_.jpg");

/*RAM*/
INSERT INTO prodotti values (10, "Corsair Vengeance 16GB", "RAM 2X8GB DDR4 3000MHz", "RAM",96.99, 15, 22, "https://images-na.ssl-images-amazon.com/images/I/71EXOwFSf-L._AC_SL1500_.jpg");
INSERT INTO prodotti values (11, "Crucial Ballistix 16GB", "RAM 2X8GB DDR4 3600MHz", "RAM",94.99, 10, 22, "https://www.scontify.net/wp-content/uploads/2020/10/crucial-ballistix-bl2k8g32c16u4b-3200-mhz-ddr4-dram-16gb-8gb-x2-1.png");
INSERT INTO prodotti values (12, "HyperZ Fury 8GB", "RAM 2X4GB DDR4 2666MHz", "RAM",46.99, 25, 22, "https://images-na.ssl-images-amazon.com/images/I/61Udx3CqBtL._AC_SY450_.jpg");

/*Case*/
INSERT INTO prodotti values (13, "Cooler Master TD500", "ARGB con stile poligonale", "Case",92.99, 10, 22, "https://www.alternate.it/p/600x600/t/Cooler_Master_MasterBox_TD500_Mesh_Midi_Tower_Nero__Chassis_Tower@@tqxm5z0e.jpg");
INSERT INTO prodotti values (14, "Be Quiet Pure 500DX", "ARGB, Ultra silenzioso", "Case",123.99, 12, 22, "https://images-na.ssl-images-amazon.com/images/I/71OBC-gzktL._AC_SL1500_.jpg");

/*Alimentatori*/
INSERT INTO prodotti values (15, "Corsair VS 650", "650 W PFC Attivo 80 Plus", "PSU", 122.99, 10, 22, "https://images-na.ssl-images-amazon.com/images/I/61j6S1AE3xL._AC_SX679_.jpg");
INSERT INTO prodotti values (16, "Thermaltake Smart Pro", "750 W Modular 80+Bronze", "PSU", 115.97, 23, 22, "https://thermaltake.azureedge.net/pub/media/catalog/product/cache/e4fc6e308b66431a310dcd4dc0838059/db/imgs/pdt/angle/PS-SPR-0750FPCBxx-R_13cd6177fc144663864a415017c54b94.jpg");
INSERT INTO prodotti values (17, "Cooler Master MWE 750", "White-V2 750 80 Plus", "PSU", 70.91, 7, 22, "https://mitelcomputer.com/97680-thickbox_default/cooler-master-mwe-gold-750-v2-alimentatore-per-computer-750-w-24-pin-atx-atx-nero.jpg");

/*Scheda Madre*/
INSERT INTO prodotti values (18, "ASUS PRIME Z590-A", "Intel Z590 ATX PCle 4.0 3x slot M.2", "MOBO", 250.00, 3, 22, "https://images-na.ssl-images-amazon.com/images/I/811BrUAgO%2BL._AC_SL1500_.jpg");
INSERT INTO prodotti values (19, "MSI MPG Z490", "ATX, 10 Gen Intel Core, LGA 1200", "MOBO", 287.00, 7, 22, "https://images-na.ssl-images-amazon.com/images/I/91D8uWFdGDL._AC_SL1500_.jpg");
INSERT INTO prodotti values (20, "ROG STRIX B450-F", "Supporto DDR4 a 3200 MHz AM4", "MOBO", 119.33, 1, 22, "https://images-na.ssl-images-amazon.com/images/I/61Wrp3hrfKL._AC_SL1000_.jpg");

/*CPU FAN*/
INSERT INTO prodotti values (21, "Cooler Master Hyper 212", "Ventola a 4 tubi", "CPU FAN", 33.15, 22, 22, "https://images-na.ssl-images-amazon.com/images/I/81zrLw2kuJL._AC_SL1372_.jpg");
INSERT INTO prodotti values (22, "ARCTIC Freezer 34 ", "2 ventole da PWM 120 mm", "CPU FAN", 40.00, 7, 22, "https://images-na.ssl-images-amazon.com/images/I/61U%2B-vptquL._AC_SL1200_.jpg");

/*HDD e SSD*/
INSERT INTO prodotti values (23, "SEAGATE 2TB", "2 TB 7200 giri/min", "Storage", 48.00, 29, 22, "https://www.discoazul.it/uploads/media/images/disco-duro-seagate-barracuda-2tb-sata-3-3-5-1.jpg");
INSERT INTO prodotti values (24, "SEAGATE 1TB", "1TB 6Gbit/s 3.5", "Storage", 35.00, 3, 22, "https://i.ebayimg.com/images/g/-G4AAOSw2LFeNtbA/s-l640.jpg");
INSERT INTO prodotti values (25, "Netac NVMe 500GB", "500 GB Gen3 * 4 PCle M.2 2280", "Storage", 66.00, 2, 22, "https://m.media-amazon.com/images/I/615dU3gHanL._AC_SS450_.jpg");

/*Periferiche*/
INSERT INTO prodotti values (26, "Trust Gaming GXT 845", "Luci LED, 12 Tasti Macro", "Periferica",33.90, 8, 22, "https://m.media-amazon.com/images/I/81X3TdjAb-L._AC_SS450_.jpg");
INSERT INTO prodotti values (27, "Logitech K120", "USB Plug-And-Play, Tasti Silenziosi", "Periferica",15.11, 4, 22, "https://images-na.ssl-images-amazon.com/images/I/412uabxHiNL._AC_SX450_.jpg");
INSERT INTO prodotti values (28, "Razer BlackWidow V3", "Illuminazione Chroma RGB", "Periferica", 163.98, 6, 22, "https://plecom.imgix.net/iil-240548-643786.jpg?fit=fillmax&fill=solid&fill-color=ffffff&auto=format&w=1000&h=1000");

INSERT INTO prodotti values (29, "Razer Basilisk V2", "Sensore ottico 20K DPI", "Periferica",89.99, 7, 22, "https://www.manualeduso.it/thumbs/products/l/1202013-razer-basilisk-v2.jpg");
INSERT INTO prodotti values (30, "Logitech MX Master 3", "Wireless con Ricevitore Bluetooth", "Periferica",98.99, 9, 22, "https://www.logitech.com/content/dam/logitech/en/products/mice/mx-master-3/gallery/mx-master-product-gallery-graphite-top.png");

INSERT INTO prodotti values (31, "AVM FRITZ Powerline ", "x2 Adattatori, fino a 500 Mbit/s", "Periferica", 49.99, 5, 22, "https://images-na.ssl-images-amazon.com/images/I/618jjWF5kjL._AC_SY450_.jpg");
INSERT INTO prodotti values (32, "TP-Link TX401 ", "10 Gigabit PCIe", "Periferica", 99.00, 10, 22, "https://images-na.ssl-images-amazon.com/images/I/61c-YqEutLL._AC_SY450_.jpg");

INSERT INTO prodotti values (33, "Logitech Altoparlanti", "10W, 2 Ingressi 3.5 mm", "Periferica", 35.00, 8, 22, "https://images-na.ssl-images-amazon.com/images/I/61rc5cC2QML._AC_SY450_.jpg");

INSERT INTO prodotti values (34, "Galaxy Buds Live", "Wireless In-Ear", "Periferica", 120.00, 15, 22, "https://www.elettrocasa.it/78287-medium_default/samsung-galaxy-buds-live-mystic-black.jpg");
INSERT INTO prodotti values (35, "Logitech G635 Cuffie", "Audio Surround 7.1", "Periferica", 120.00, 10, 22, "https://resource.logitechg.com/content/dam/gaming/en/products/g635/g635-hero.png");
INSERT INTO prodotti values (36, "PlayStation�5 Controller", "Wireless Controller PS", "Periferica", 60.00, 20, 22, "https://gmedia.playstation.com/is/image/SIEPDC/dualsense-thumbnail-ps5-01-en-17jul20?$native$");

/*Utenti*/
INSERT INTO utenti values("CicaMatt", MD5("Mattcica69"), "Matteo", "Cicalese", true);
INSERT INTO utenti values("nnoglia", MD5("Kevin14"), "Kevin", "Pacifico", true);
INSERT INTO utenti values("zengr098", MD5("Zingaro98"), "Mattia", "Sapere", false);
INSERT INTO utenti values("Stefano00", MD5("Rossana70"), "Stefano", "Marciano", false);

INSERT INTO pagamenti values(1, "Stefano00", "4023121212121212", 333, 11/25);
INSERT INTO pagamenti values(2, "zengr098", "4023123412121212", 543, 01/28);

INSERT INTO indirizzi values(1, "Stefano00", "Via Roma", "32", "84015", "Eboli", "SA");
INSERT INTO indirizzi values(2, "zengr098", "Via De Felice", "36", "84018", "Antessano", "SA");

INSERT INTO ordini values(1, "Stefano00", 5, "2021-04-16", 3, 56.12, 1, 1);
INSERT INTO ordini values(2, "zengr098", 8, "2021-04-12", 3, 56.12, 2, 2);