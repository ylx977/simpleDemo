package com.demo.seed;

/**
 * Created by jqz on 2018/1/2 14:06
 * Explain
 */

public class CityDictionary {

    private static CityDictionary instance;

    public static CityDictionary getInstance(){
        if(instance == null){
            instance = new CityDictionary();
        }
        return instance;
    }

    private CityDictionary(){
    }

    public String [] getOther(){
        return other;
    }

    private final String [] other = {
            "公司",
            "集团",
            "投资",
            "科技",
            "有限",
            "责任",
            "金融",
            "服务",
            "信息",
            "股份",
            "外包",
            "）",
            "（",

    };


    public String [] getCity(){
        return city;
    }

    private final String [] city = {
            "晋州",
            "北京",
            "丰南",
            "沙河",
            "高碑店",
            "河间",
            "深州",
            "张家口",
            "衡水",
            "新乐",
            "迁安",
            "涿州",
            "泊头",
            "霸州",
            "太原",
            "长治",
            "古交",
            "原平",
            "榆次",
            "霍州",
            "大同",
            "晋城",
            "潞城",
            "孝义",
            "介休",
            "运城",
            "阳泉",
            "朔州",
            "高平",
            "离石",
            "临汾",
            "永济",
            "忻州",
            "汾阳",
            "侯马",
            "河津",
            "呼和浩特",
            "赤峰",
            "霍林郭勒",
            "牙克石",
            "二连浩特",
            "东胜",
            "包头",
            "通辽",
            "海拉尔",
            "根河",
            "锡林浩特",
            "临河",
            "乌海",
            "满洲里",
            "额尔古纳",
            "集宁",
            "扎兰屯",
            "乌兰浩特",
            "丰镇",
            "沈阳",
            "抚顺",
            "营口",
            "铁岭",
            "新民",
            "海城",
            "北宁",
            "铁法",
            "兴城",
            "长春",
            "辽源",
            "白城",
            "九台",
            "桦甸",
            "双辽",
            "洮南",
            "敦化",
            "哈尔滨",
            "鹤岗",
            "佳木斯",
            "阿城",
            "讷河",
            "同江",
            "宁安",
            "绥化",
            "上海",
            "南京",
            "常州",
            "淮阴",
            "泰州",
            "江阴",
            "邳州",
            "常熟",
            "太仓",
            "通州",
            "大丰",
            "丹阳",
            "靖江",
            "杭州",
            "嘉兴",
            "衢州",
            "萧山",
            "临安",
            "瑞安",
            "桐乡",
            "兰溪",
            "江山",
            "龙泉",
            "合肥",
            "淮南",
            "安庆",
            "宿州",
            "桐城",
            "界首",
            "福州",
            "三明",
            "龙岩",
            "福清",
            "晋江",
            "武夷山",
            "宁德",
            "南昌",
            "九江",
            "乐平",
            "南康",
            "高安",
            "井冈山",
            "济南",
            "枣庄",
            "济宁",
            "莱芜",
            "章丘",
            "胶南",
            "莱阳",
            "栖霞",
            "寿光",
            "曲阜",
            "肥城",
            "乐陵",
            "菏泽",
            "郑州",
            "平顶山",
            "焦作",
            "三门峡",
            "巩义",
            "登封",
            "林州",
            "沁阳",
            "义马",
            "周口",
            "武汉",
            "宜昌",
            "孝感",
            "大冶",
            "枝江",
            "钟祥",
            "汉川",
            "麻城",
            "利川",
            "天门",
            "长沙",
            "衡阳",
            "张家界",
            "怀化",
            "浏阳",
            "耒阳",
            "临湘",
            "洪江",
            "广州",
            "珠海",
            "湛江",
            "梅州",
            "清远",
            "揭阳",
            "番禺",
            "乐昌",
            "顺德",
            "台山",
            "恩平",
            "高州",
            "四会",
            "阳春",
            "罗定",
            "南宁",
            "梧州",
            "贵港",
            "岑溪",
            "凭祥",
            "河池",
            "海口",
            "通什",
            "文昌",
            "重庆",
            "江津",
            "成都",
            "泸州",
            "遂宁",
            "宜宾",
            "都江堰",
            "广汉",
            "峨眉山",
            "雅安",
            "简阳",
            "贵阳",
            "清镇",
            "兴义",
            "都匀",
            "昆明",
            "安宁",
            "个旧",
            "大理",
            "西安",
            "咸阳",
            "榆林",
            "兴平",
            "商州",
            "兰州",
            "白银",
            "玉门",
            "武威",
            "合作",
            "西宁",
            "格尔木",
            "银川",
            "青铜峡",
            "乌鲁木齐",
            "吐鲁番",
            "米泉",
            "阿图什",
            "伊宁",
            "大连",
            "本溪",
            "阜新",
            "朝阳",
            "瓦房店",
            "东港",
            "盖州",
            "开原",
            "吉林",
            "通化",
            "榆树",
            "舒兰",
            "梅河口",
            "大安",
            "珲春",
            "齐齐哈尔",
            "双鸭山",
            "七台河",
            "双城",
            "虎林",
            "富锦",
            "穆棱",
            "安达",
            "无锡",
            "苏州",
            "盐城",
            "宿迁",
            "宜兴",
            "溧阳",
            "张家港",
            "吴县",
            "海门",
            "仪征",
            "扬中",
            "泰兴",
            "宁波",
            "湖州",
            "舟山",
            "建德",
            "余姚",
            "乐清",
            "诸暨",
            "义乌",
            "温岭",
            "芜湖",
            "马鞍山",
            "黄山",
            "巢湖",
            "天长",
            "宣州",
            "厦门",
            "泉州",
            "长乐",
            "南安",
            "建瓯",
            "福安",
            "景德镇",
            "新余",
            "瑞昌",
            "宜春",
            "上饶",
            "临川",
            "青岛",
            "东营",
            "泰安",
            "临沂",
            "胶州",
            "莱西",
            "莱州",
            "海阳",
            "安丘",
            "兖州",
            "文登",
            "禹城",
            "开封",
            "安阳",
            "濮阳",
            "南阳",
            "荥阳",
            "偃师",
            "卫辉",
            "孟州",
            "灵宝",
            "项城",
            "黄石",
            "襄樊",
            "荆州",
            "丹江口",
            "老河口",
            "应城",
            "石首",
            "武穴",
            "随州",
            "株洲",
            "邵阳",
            "益阳",
            "娄底",
            "醴陵",
            "常宁",
            "津市",
            "冷水江",
            "韶关",
            "汕头",
            "茂名",
            "汕尾",
            "东莞",
            "云浮",
            "花都",
            "南雄",
            "南海",
            "新会",
            "廉江",
            "化州",
            "惠阳",
            "英德",
            "柳州",
            "北海",
            "玉林",
            "东兴",
            "合山",
            "宜州",
            "三亚",
            "琼海",
            "万宁",
            "合川",
            "自贡",
            "德阳",
            "内江",
            "广安",
            "彭州",
            "什邡",
            "阆中",
            "西昌",
            "六盘水",
            "赤水",
            "毕节",
            "福泉",
            "曲靖",
            "宣威",
            "开远",
            "保山",
            "铜川",
            "渭南",
            "韩城",
            "嘉峪关",
            "天水",
            "酒泉",
            "平凉",
            "德令哈",
            "石嘴山",
            "灵武",
            "克拉玛依",
            "哈密",
            "博乐",
            "喀什",
            "塔城",
            "鞍山",
            "丹东",
            "辽阳",
            "葫芦岛",
            "普兰店",
            "凤城",
            "大石桥",
            "北票",
            "四平",
            "白山",
            "德惠",
            "磐石",
            "集安",
            "延吉",
            "龙井",
            "鸡西",
            "大庆",
            "牡丹江",
            "尚志",
            "密山",
            "绥芬河",
            "北安",
            "肇东",
            "徐州",
            "南通",
            "扬州",
            "锡山",
            "金坛",
            "昆山",
            "启东",
            "淮安",
            "高邮",
            "句容",
            "姜堰",
            "温州",
            "绍兴",
            "台州",
            "富阳",
            "慈溪",
            "海宁",
            "上虞",
            "东阳",
            "临海",
            "蚌埠",
            "淮北",
            "滁州",
            "六安",
            "明光",
            "宁国",
            "莆田",
            "漳州",
            "永安",
            "龙海",
            "建阳",
            "福鼎",
            "萍乡",
            "鹰潭",
            "贵溪",
            "丰城",
            "德兴",
            "淄博",
            "烟台",
            "威海",
            "德州",
            "即墨",
            "滕州",
            "蓬莱",
            "青州",
            "高密",
            "邹城",
            "荣成",
            "临清",
            "洛阳",
            "鹤壁",
            "许昌",
            "商丘",
            "新密",
            "舞钢",
            "辉县",
            "禹州",
            "邓州",
            "驻马店",
            "十堰",
            "鄂州",
            "黄冈",
            "枝城",
            "枣阳",
            "安陆",
            "洪湖",
            "赤壁",
            "仙桃",
            "湘潭",
            "岳阳",
            "郴州",
            "湘乡",
            "武冈",
            "沅江",
            "涟源",
            "深圳",
            "佛山",
            "肇庆",
            "河源",
            "中山",
            "增城",
            "潮阳",
            "三水",
            "开平",
            "雷州",
            "信宜",
            "兴宁",
            "连州",
            "桂林",
            "防城港",
            "桂平",
            "贺州",
            "儋州",
            "东方",
            "永川",
            "攀枝花",
            "绵阳",
            "乐山",
            "达州",
            "邛崃",
            "绵竹",
            "华蓥",
            "巴中",
            "遵义",
            "仁怀",
            "安顺",
            "玉溪",
            "昭通",
            "思茅",
            "瑞丽",
            "宝鸡",
            "延安",
            "华阴",
            "金昌",
            "敦煌",
            "西峰",
            "吴忠",
            "昌吉",
            "库尔勒",
            "和田",
            "乌苏",
            "锦州",
            "盘锦",
            "庄河",
            "凌海",
            "灯塔",
            "凌源",
            "松原",
            "蛟河",
            "公主岭",
            "临江",
            "图们",
            "和龙",
            "伊春",
            "黑河",
            "五常",
            "铁力",
            "海林",
            "五大连池",
            "海伦",
            "连云港",
            "镇江",
            "新沂",
            "武进",
            "吴江",
            "如皋",
            "东台",
            "江都",
            "兴化",
            "金华",
            "余杭",
            "奉化",
            "平湖",
            "嵊州",
            "永康",
            "丽水",
            "铜陵",
            "阜阳",
            "亳州",
            "贵池",
            "南平",
            "石狮",
            "邵武",
            "漳平",
            "赣州",
            "瑞金",
            "樟树",
            "吉安",
            "潍坊",
            "日照",
            "聊城",
            "平度",
            "龙口",
            "招远",
            "诸城",
            "昌邑",
            "新泰",
            "乳山",
            "滨州",
            "新乡",
            "漯河",
            "信阳",
            "新郑",
            "汝州",
            "济源",
            "长葛",
            "永城",
            "荆门",
            "咸宁",
            "当阳",
            "宜城",
            "广水",
            "松滋",
            "恩施",
            "潜江",
            "常德",
            "永州",
            "韶山",
            "汩罗",
            "资兴",
            "吉首",
            "江门",
            "惠州",
            "阳江",
            "潮州",
            "从化",
            "澄海",
            "高明",
            "鹤山",
            "吴川",
            "高要",
            "陆丰",
            "普宁",
            "钦州",
            "北流",
            "百色",
            "琼山",
            "南川",
            "广元",
            "南充",
            "崇州",
            "江油",
            "万源",
            "资阳",
            "铜仁",
            "凯里",
            "楚雄",
            "景洪",
            "潞西",
            "汉中",
            "安康",
            "张掖",
            "临夏",
            "阜康",
            "阿克苏",
            "奎屯",
            "阿勒泰",
            "河北",
            "山西",
            "辽宁",
            "吉林",
            "黑龙江",
            "江苏",
            "浙江",
            "安徽",
            "福建",
            "江西",
            "山东",
            "河南",
            "湖北",
            "湖南",
            "广东",
            "海南",
            "四川",
            "贵州",
            "云南",
            "陕西",
            "甘肃",
            "青海",
            "内蒙古",
            "广西壮族",
            "广西",
            "西藏",
            "宁夏回族",
            "宁夏",
            "新疆维吾尔",
            "新疆",

    };

}