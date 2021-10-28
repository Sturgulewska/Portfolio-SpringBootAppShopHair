INSERT INTO `category` (`id`, `name`) VALUES
(1, '2a'),
(2, '2b'),
(3, '2c'),
(4, '3a'),
(5, '3b');

INSERT INTO `client` (`Id`, `name`, `street`, `city`, `state`, `zip`, `cc_number`, `cc_expiration`, `cc_cvv`, `email`) VALUES
(1, 'Samuel Long', 'Machocka 4', 'Kielce', 'Podlaskie', '16-333', '4085232887400058', '04/2023', '233', 'long@wp.pl'),
(2, 'Przemko Wysocki', 'Pomologiczna 155', 'Warszawa', 'Mazowieckie', '04-859', '5370187958148203', '12/2024', '504', 'wysocki@gmail.com'),
(3, 'Karina Czerwinska', 'GarczyĹ„ska 15', 'GdaĹ„sk', 'Pomorskie', '80-174', '4004154478589008', '05/2023', '333', 'fake.annaemail@yahoo.pl');

INSERT INTO `order_product` (`id`, `product_id`, `order_id`) VALUES
(1, 7, 1),
(2, 2, 2),
(3, 7, 1),
(4, 2, 1);

INSERT INTO `product` (`id`, `name`, `netto`, `brutto`, `category_id`) VALUES
(1, 'HAIRY TALE COSMETICS CURLMELON - MOCNO UTRWALAJÄ„CY Ĺ»EL DO FAL I LOKĂ“W 250ML\r\n', 47.45, 50.5, 1),
(2, 'ALKEMILLA ODBUDOWUJÄ„CA MASKA DO WĹOSĂ“W SUCHYCH I ZNISZCZONYCH 200ML\r\n', 32.12, 48.9, 4),
(3, 'ANWEN NAWILĹ»AJÄ„CY BEZ - ODĹ»YWKA DO WĹOSĂ“W O RĂ“Ĺ»NEJ POROWATOĹšCI 200ML\r\n', 21.17, 35.45, 4),
(4, 'ANWEN NAWILĹ»AJÄ„CY BEZ - ODĹ»YWKA DO WĹOSĂ“W O RĂ“Ĺ»NEJ POROWATOĹšCI 200ML\r\n', 16.88, 20.7, 3),
(5, 'JESSICURL ALOEBA DAILY CONDITIONER NO FRAGRANCE ADDED ODĹ»YWKA BEZ SPĹUKIWANIA BEZZAPACHOWA 237ML\r\n', 47.99, 62.3, 1),
(6, 'CANTU MOISTURIZING CURL ACTIVATOR CREAM- AKTYWATOR SKRÄTU 355ML\r\n', 32.8, 50.2, 2),
(7, 'BYDGOSKA WYTWĂ“RNIA MYDĹA PODKRÄCONA MASKA DO WĹOSĂ“W - SPIRULINA I BANAN DUĹ»A 200ML\r\n', 43.55, 50.8, 5);


INSERT INTO `shop_order` (`id`, `hash`, `confirmed`, `date`, `client_id`) VALUES
(1, 'd41d8cd98f00b204e9800998ecf8427e', '0', '2021-10-11', 1),
(2, 'c81e728d9d4c2f636f067f89cc14862c', '0', '2021-08-31', 2),
(3, '63757260-1fac-4985-8d61-4a2deaac2ffc', '0', '2021-10-26', 1),
(4, 'c1aa9ab9-13bd-426e-a9f0-688aeaf96545', '1', '2021-10-26', 3);

