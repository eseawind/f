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
