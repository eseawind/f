/* 20151012 已执行 */
ALTER TABLE cgoods DROP COLUMN aprice;
ALTER TABLE gdetail DROP COLUMN descript;
ALTER TABLE goods ADD COLUMN descript VARCHAR(2000) COMMENT '详情描述';

/* 20151015 已执行*/
DROP TABLE gdetail;
ALTER TABLE cgoods ADD COLUMN photo VARCHAR(254) COMMENT '主图';
ALTER TABLE cgoods ADD COLUMN photo1 VARCHAR(254) COMMENT '图2';
ALTER TABLE cgoods ADD COLUMN photo2 VARCHAR(254) COMMENT '图3';
ALTER TABLE cgoods ADD COLUMN photo3 VARCHAR(254) COMMENT '图4';
ALTER TABLE cgoods ADD COLUMN photo4 VARCHAR(254) COMMENT '图5';
DROP TABLE oaddress;
ALTER TABLE orders ADD COLUMN provinceId INT(11) NOT NULL COMMENT '省id';
ALTER TABLE orders ADD COLUMN cityId INT(11) NOT NULL COMMENT '市id';
ALTER TABLE orders ADD COLUMN areaId INT(11) NOT NULL COMMENT '区id';
ALTER TABLE orders ADD COLUMN provinceName VARCHAR(50) NOT NULL COMMENT '省名称';
ALTER TABLE orders ADD COLUMN cityName VARCHAR(50) NOT NULL COMMENT '市名称';
ALTER TABLE orders ADD COLUMN areaName VARCHAR(50) NOT NULL COMMENT '区名称';
ALTER TABLE orders ADD COLUMN remark VARCHAR(254) NOT NULL COMMENT '详细地址';
ALTER TABLE orders ADD COLUMN consignee VARCHAR(20) NOT NULL COMMENT '收货人';
ALTER TABLE orders ADD COLUMN mobile VARCHAR(20) NOT NULL COMMENT '收货人手机号';

/* 20151017 已执行*/
DROP TABLE gcategory;
ALTER TABLE goods ADD COLUMN code VARCHAR(9) NOT NULL COMMENT '品类code';
ALTER TABLE goods DROP COLUMN sku;
ALTER TABLE cgoods ADD COLUMN sku VARCHAR(32) NULL COMMENT 'sku';

/*20151027 未执行*/
ALTER TABLE uaddress MODIFY COLUMN remark VARCHAR(254) NOT NULL COMMENT '详细地址';

/* 20151020 已执行*/
DROP TABLE gstock;
ALTER TABLE cgoods ADD COLUMN number INT(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'number';

/* 20151020 已执行*/
CREATE UNIQUE INDEX category_code ON category(code);
UPDATE cgoods SET sku = NULL WHERE sku = '';
CREATE UNIQUE INDEX cgoods_sku ON cgoods(sku);
CREATE UNIQUE INDEX favorite_userId_cgoodsId on favorite(userId,cgoodsId);
CREATE UNIQUE INDEX husers_username ON husers(username);
CREATE UNIQUE INDEX merchant_username ON merchant(username);
CREATE UNIQUE INDEX users_username ON users(username); 

/* 20151022 已执行*/
CREATE TABLE `carts` (
  `userId` bigint(20) NOT NULL COMMENT '用户id',
  `cartStr` varchar(1000) NOT NULL COMMENT '购物车',
  `modifytime` datetime NOT NULL COMMENT '最后操作时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* 20151022 已执行 */
alter table users modify column mobile varchar(20) comment '手机号';
