package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商品详情
 * 
 * @author bowensun
 */
public class GoodsInfoVo {

	/** 商品编号 **/
	private String goodsId;

	/** 商品名称 **/
	private String goodsName;

	/** 商品规格 **/
	private String specification;

	/** 销售单位 **/
	private String unit;

	/** 商品类型 **/
	private Integer type;

	/** 商品标语 **/
	private String slogan;

	/** 商品备注 **/
	private String remark;

	/** 创建人编码 **/
	private String createUid;

	/** 创建时间 **/
	private Date createTime;

	/** 修改人编码 **/
	private String updateUid;

	/** 修改时间 **/
	private Date updateTime;

	/** 删除标识,0未删除，1删除 **/
	private Integer deleted;

	/** 商品扩展表字段 **/
	/** 商品属性：1直发，2寄存 **/
	private Integer goodsProperty;

	/** 商品价格 **/
	private BigDecimal goodsPrice;

	/** 会员价格 **/
	private BigDecimal vipPrice;

	/** 是否结算 0：不结算，1：结算 **/
	private Integer isSettle;

	/** 结算价格：> 0 为固定价格，等于0，为实付价格 **/
	private BigDecimal settlePrice;

	/** 商品邮递类型：0不包邮，1包邮 **/
	private Integer goodsFreight;

	/** 是否允许退货：0不允许，1允许 **/
	private Integer allowBack;

	/** 售卖类型 1不限，2限时 **/
	private Integer sellType;

	/** 售卖开始时间 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sellBtime;

	/** 售卖结束时间 **/
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sellEtime;

	/** 购买使用积分：0不可使用，大于0表示使用的积分数 **/
	private Integer usePoint;

	/** 购买赠送积分数 **/
	private Integer getPoint;

	/** 购买赠送积分发放形式：1一次性，2分期 **/
	private Integer getPointType;

	/** 购买赠送积分发放周数 **/
	private Integer getPointPeriod;

	/** 赠送邀请人积分数 **/
	private Integer invitePoint;

	/** 邀请赠送积分类型：1一次性，2分期 **/
	private Integer invitePointType;

	/** 邀请赠送积分发放周数 **/
	private Integer invitePointPeriod;

	/** 商品使用状态：0下架，1上架 **/
	private Integer goodsStatus;

	/** 商品状态表 **/
	/** 商品是否限购 */
	private Integer isLimitSell;

	/** 商品库存量 **/
	private Integer goodsStock;

	/** 销量 */
	private Integer goodsSellcount;

	/** 浏览量 */
	private Integer goodsViewcount;

	/** 产品编码 */
	private Long productId;

	/** 产品数量 */
	private Integer num;

	/** 产品名称 **/
	private String productName;

	/** 商品公共小图文件名称 **/
	private String imageName;

	/** 商品公共小图访问地址 **/
	private String imageUrl;

	public GoodsInfoVo(String goodsId, String goodsName, String specification, String unit, Integer type, String slogan,
			String remark, String createUid, Date createTime, String updateUid, Date updateTime, Integer deleted,
			Integer goodsProperty, BigDecimal goodsPrice, BigDecimal vipPrice, Integer isSettle, BigDecimal settlePrice,
			Integer goodsFreight, Integer allowBack, Integer sellType, Date sellBtime, Date sellEtime, Integer usePoint,
			Integer getPoint, Integer getPointType, Integer getPointPeriod, Integer invitePoint,
			Integer invitePointType, Integer invitePointPeriod, Integer goodsStatus, Integer isLimitSell,
			Integer goodsStock, Integer goodsSellcount, Integer goodsViewcount, Long productId, Integer num,
			String productName) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.specification = specification;
		this.unit = unit;
		this.type = type;
		this.slogan = slogan;
		this.remark = remark;
		this.createUid = createUid;
		this.createTime = createTime;
		this.updateUid = updateUid;
		this.updateTime = updateTime;
		this.deleted = deleted;
		this.goodsProperty = goodsProperty;
		this.goodsPrice = goodsPrice;
		this.vipPrice = vipPrice;
		this.isSettle = isSettle;
		this.settlePrice = settlePrice;
		this.goodsFreight = goodsFreight;
		this.allowBack = allowBack;
		this.sellType = sellType;
		this.sellBtime = sellBtime;
		this.sellEtime = sellEtime;
		this.usePoint = usePoint;
		this.getPoint = getPoint;
		this.getPointType = getPointType;
		this.getPointPeriod = getPointPeriod;
		this.invitePoint = invitePoint;
		this.invitePointType = invitePointType;
		this.invitePointPeriod = invitePointPeriod;
		this.goodsStatus = goodsStatus;
		this.isLimitSell = isLimitSell;
		this.goodsStock = goodsStock;
		this.goodsSellcount = goodsSellcount;
		this.goodsViewcount = goodsViewcount;
		this.productId = productId;
		this.num = num;
		this.productName = productName;
	}

	public GoodsInfoVo() {
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateUid() {
		return createUid;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUid() {
		return updateUid;
	}

	public void setUpdateUid(String updateUid) {
		this.updateUid = updateUid;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getGoodsProperty() {
		return goodsProperty;
	}

	public void setGoodsProperty(Integer goodsProperty) {
		this.goodsProperty = goodsProperty;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(BigDecimal vipPrice) {
		this.vipPrice = vipPrice;
	}

	public Integer getIsSettle() {
		return isSettle;
	}

	public void setIsSettle(Integer isSettle) {
		this.isSettle = isSettle;
	}

	public BigDecimal getSettlePrice() {
		return settlePrice;
	}

	public void setSettlePrice(BigDecimal settlePrice) {
		this.settlePrice = settlePrice;
	}

	public Integer getGoodsFreight() {
		return goodsFreight;
	}

	public void setGoodsFreight(Integer goodsFreight) {
		this.goodsFreight = goodsFreight;
	}

	public Integer getAllowBack() {
		return allowBack;
	}

	public void setAllowBack(Integer allowBack) {
		this.allowBack = allowBack;
	}

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}

	public Date getSellBtime() {
		return sellBtime;
	}

	public void setSellBtime(Date sellBtime) {
		this.sellBtime = sellBtime;
	}

	public Date getSellEtime() {
		return sellEtime;
	}

	public void setSellEtime(Date sellEtime) {
		this.sellEtime = sellEtime;
	}

	public Integer getUsePoint() {
		return usePoint;
	}

	public void setUsePoint(Integer usePoint) {
		this.usePoint = usePoint;
	}

	public Integer getGetPoint() {
		return getPoint;
	}

	public void setGetPoint(Integer getPoint) {
		this.getPoint = getPoint;
	}

	public Integer getGetPointType() {
		return getPointType;
	}

	public void setGetPointType(Integer getPointType) {
		this.getPointType = getPointType;
	}

	public Integer getGetPointPeriod() {
		return getPointPeriod;
	}

	public void setGetPointPeriod(Integer getPointPeriod) {
		this.getPointPeriod = getPointPeriod;
	}

	public Integer getInvitePoint() {
		return invitePoint;
	}

	public void setInvitePoint(Integer invitePoint) {
		this.invitePoint = invitePoint;
	}

	public Integer getInvitePointType() {
		return invitePointType;
	}

	public void setInvitePointType(Integer invitePointType) {
		this.invitePointType = invitePointType;
	}

	public Integer getInvitePointPeriod() {
		return invitePointPeriod;
	}

	public void setInvitePointPeriod(Integer invitePointPeriod) {
		this.invitePointPeriod = invitePointPeriod;
	}

	public Integer getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Integer getIsLimitSell() {
		return isLimitSell;
	}

	public void setIsLimitSell(Integer isLimitSell) {
		this.isLimitSell = isLimitSell;
	}

	public Integer getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Integer goodsStock) {
		this.goodsStock = goodsStock;
	}

	public Integer getGoodsSellcount() {
		return goodsSellcount;
	}

	public void setGoodsSellcount(Integer goodsSellcount) {
		this.goodsSellcount = goodsSellcount;
	}

	public Integer getGoodsViewcount() {
		return goodsViewcount;
	}

	public void setGoodsViewcount(Integer goodsViewcount) {
		this.goodsViewcount = goodsViewcount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
