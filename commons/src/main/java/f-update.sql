/* 20151012 已执行 */
ALTER TABLE cgoods DROP COLUMN aprice;
ALTER TABLE gdetail DROP COLUMN descript;
ALTER TABLE goods ADD COLUMN descript VARCHAR(2000) COMMENT '详情描述';
