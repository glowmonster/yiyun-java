package top.glowmonster.base.em;

public enum OperationModuleEnum {
    MODULE_USER(101, "用户模块"),
    MODULE_SONG_STYLE(102, "歌曲风格模块"),
    MODULE_SONG_LIST(103, "歌单模块"),
    MODULE_SONG(104, "歌曲模块"),
    MODULE_SONG_COMMENT(105, "歌曲评论模块")
    ;

    OperationModuleEnum(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    private int code;
    private String describe;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OperationModuleEnum{");
        sb.append("code=").append(code);
        sb.append(", describe='").append(describe).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
