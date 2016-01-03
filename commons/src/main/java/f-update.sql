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

/**20151028 已执行*/
ALTER TABLE uaddress ADD COLUMN isDef INT NOT NULL DEFAULT 0 COMMENT '默认收货地址';

/**20151029 已执行*/
ALTER TABLE balancelog MODIFY COLUMN afterBalance DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '交易后余额';
ALTER TABLE odetail MODIFY COLUMN buyPrice DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '购买价格';
ALTER TABLE orders MODIFY COLUMN orderPrice DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '订单金额';
ALTER TABLE orders MODIFY COLUMN paidPrice DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '已付金额';
ALTER TABLE orders MODIFY COLUMN productPrice DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '商品总额';
ALTER TABLE orders MODIFY COLUMN discountPrice DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额';
ALTER TABLE orders MODIFY COLUMN balancePrice DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '使用余额金额';
ALTER TABLE orders MODIFY COLUMN payPrice DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '应付金额';

/**20151030 已执行*/
ALTER TABLE odetail CHANGE goodsId cgoodsId BIGINT NOT NULL COMMENT '商品规格id';
ALTER TABLE odetail ADD COLUMN sku VARCHAR(32) NULL COMMENT 'sku';
ALTER TABLE users ADD COLUMN ppassword VARCHAR(50) NULL COMMENT '支付密码';
ALTER TABLE balancelog MODIFY COLUMN money DECIMAL(10,2) NOT NULL DEFAULT 0.00 COMMENT '交易金额';

/** 20151031 已执行*/
ALTER TABLE odetail DROP COLUMN isSend;
ALTER TABLE odetail DROP COLUMN isDelivery;
ALTER TABLE odetail DROP COLUMN deliveryTime;
ALTER TABLE odetail DROP COLUMN sendTime;

/**2015113 已执行*/
ALTER TABLE orders ADD COLUMN payTime DATETIME NULL COMMENT '付款时间' AFTER payPrice;

CREATE TABLE `dynpage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(254) DEFAULT NULL COMMENT '活动名称',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '活动类型',
  `merchantId` bigint(20) NOT NULL COMMENT '商家',
  `isDel` int(11) NOT NULL DEFAULT '1' COMMENT '1正常127删除',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dcgoods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pageId` bigint(20) NOT NULL COMMENT '动态页id',
  `cgid` bigint(20) NOT NULL COMMENT '商品规格id',
  `isDel` int(11) NOT NULL DEFAULT '1' COMMENT '1正常127删除',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(254) NOT NULL,
  `fid` bigint(20) NOT NULL DEFAULT '0',
  `isDel` int(11) NOT NULL DEFAULT '1' COMMENT '1正常127删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
