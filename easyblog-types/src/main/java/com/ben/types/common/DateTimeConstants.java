package com.ben.types.common;

import java.time.format.DateTimeFormatter;

/**
 * @InterfaceName: DateTimeConstants
 * @Description: DateTimeConstants
 * @Author: benjieqiang
 * @LastChangeDate: 2025/1/3 11:00 PM
 * @Version: v1.0
 */

public interface DateTimeConstants {
    /**
     * 月-日 格式
     */
    DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");

    /**
     * 年-月-日 小时-分钟-秒
     */
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

}

