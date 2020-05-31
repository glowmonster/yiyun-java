package top.glowmonster.base.em;

public enum ErrorMessageEnum {
    SYSTEM_ERROR(-1, "系统异常,请联系管理员!"),
    FORMAT_ERROR(1001, "数据格式不正确,请重新输入"),
    LOGIN_ERROR(1002, "账号不存在或者密码错误"),

    STYLE_INSERT_ERROR(1003, "歌曲风格添加失败"),
    NO_VALUE_RECEIVED(1004, "Controller没有接收到数据"),
    NO_DATA_ERROR(1005, "没有查询到任何数据"),
    STYLE_DELETE_ERROR(1006, "未删除任何歌曲风格数据"),
    STYLE_UPDATE_ERROR(1007, "修改歌曲风格失败"),

    SONG_INSERT_ERROR(1008, "添加歌曲失败"),
    SONG_FILE_UPLOAD_ERROR(1009, "音乐文件上传失败"),
    SONG_IMAGE_UPLOAD_ERROR(1010, "音乐图片上传失败"),

    SONG_IMAGE_TYPE_ERROR(1011, "上传的图片格式不正确"),
    SONG_FILE_TYPE_ERROR(1012, "上传的音乐文件格式不正确"),
    SELECT_NO_PARAMETERS_ERROR(1013, "分页查询音乐未得到必要的参数"),
    SONG_DELETE_ERROR(1014, "歌曲删除失败"),
    SONG_UPDATE_ERROR(1015, "歌曲更新失败"),

    SONG_LIST_ADD_ERROR(1016, "添加歌单失败"),
    SONG_LIST_UPDATE_ERROR(1017, "修改歌单信息失败"),
    SONG_IDS_UPDATE_ERROR(1018, "修改song_ids失败"),
    SONG_LIST_DELETE_ERROR(1019, "删除歌单失败"),

    LOGIN_LOG_INSERT_ERROR(1020, "登陆日志添加失败"),
    OPERATION_LOG_INSERT_ERROR(1021, "操作日志添加失败"),
    LOGIN_TIMEOUT(1022, "登陆超时"),
    SONG_GREAT_ERROR(1023, "点赞失败"),
    SONG_COMMENT_INSERT_ERROR(1024, "歌曲评论添加失败"),


    USER_AVATAR_UPLOAD_ERROR(1025, "用户头像添加失败"),
    USER_AVATAR_TYPE_ERROR(1026, "用户头像类型不正确,格式必须为jpg"),
    REGISTER_ERROR(1027, "注册失败"),
    USER_UPDATE_ERROR(1028, "用户更新失败")
    ;

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
