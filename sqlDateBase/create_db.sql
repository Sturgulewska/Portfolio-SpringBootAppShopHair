CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

CREATE TABLE `client` (
  `Id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `street` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `zip` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `cc_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `cc_expiration` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `cc_cvv` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

CREATE TABLE `order_product` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `hash` varchar(100) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `order_id` int(11) NOT NULL,
  `netto_amount` float NOT NULL,
  `brutto_amount` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(250) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `netto` float NOT NULL,
  `brutto` float NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

CREATE TABLE `shop_order` (
  `id` int(11) NOT NULL,
  `hash` varchar(250) COLLATE utf8mb4_polish_ci NOT NULL,
  `confirmed` varchar(50) COLLATE utf8mb4_polish_ci NOT NULL,
  `date` date NOT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;

ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `client`
  ADD PRIMARY KEY (`Id`);

ALTER TABLE `order_product`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `shop_order`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `client`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `order_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

ALTER TABLE `shop_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;
