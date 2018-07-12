package com.example.demo.common;


/**
 * 常量枚举类
 *
 * @author bowensun
 * @since 2018/6/20
 */
public interface Constant {
    /**
     * 微信模板ID
     *
     */
    enum TemplatedId implements Constant{
        /** 审核通知微信模板ID **/
        approveWechatTemplateId("ru_YmjNWbyK2nhJBAQbOv4rv3Ye64Kq1Y3HqcjqRIds"),
        ;

        private String id;

        TemplatedId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    /**
     * 导出excel表格列名
     *
     */
    enum Excelexport implements Constant{
        /** 导出发货单 **/
        deliveryInfoFieldNames(new String[]{"payTime", "deliveryCode", "createName", "userPhone", "productName", "deliveryRealnum",
                "productUnit", "liftSelf", "receiverProvince", "receiverCity", "receiverArea", "receiverName",
                "receiverPhone", "detailAddress", "ReceiveAddress", "expressComName", "expressCodes"}),
        deliverInfoTitles(new String[]{"支付时间", "提货单号", "会员姓名", "会员账号", "产品名称", "实发数量","单位","是否自提","省","市","区","收件人",
                "收件人手机号", "详细地址", "用户地址", "快递名称", "快递单号"}),
        ;

        private String[] Names;

        Excelexport(String[] names) {
            Names = names;
        }

        public String[] getNames() {
            return Names;
        }
    }

    /**
     * contentType
     *
     */
    enum contentType implements  Constant{
        /** 原生form表单**/
        formUrlencoded("application/x-www-form-urlencoded"),
        /** 上传文件 **/
        multipart("multipart/form-data"),
        /** json **/
        json("application/json"),
        /** xml **/
        xml("text/xml")
        ;

        private String type;

        contentType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    /**
     * 远程调用其他模块接口url
     *
     */
    enum interfaceUrl implements Constant{
        /** 发送微信消息接口**/
        sendWechatMessage("http://api.yogovi.vip/api/i/7004"),
        /** 支付宝支付接口**/
        aliPay("http://api.yogovi.vip/api/e/5000"),
        /** POS支付审核通知**/
        posApproveInform("http://api.yogovi.vip/api/e/5004"),
        /** 查询省下所有市**/
        selecCitiestByPid("http://api.yogovi.vip/api/a/67"),
        ;

        private String url;

        interfaceUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
