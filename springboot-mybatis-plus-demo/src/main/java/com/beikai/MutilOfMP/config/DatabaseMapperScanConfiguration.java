package com.beikai.MutilOfMP.config;/**
 * Created with IntelliJ IDEA.
 * User: beikai
 * Date: 2020/4/22
 * Time: 17:57
 * Description:
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName:
 * @Description:
 * @author hbk
 * @date 2020/4/22 17:57
 */
@Configuration
@MapperScan("com.beikai.mutilOfMP.**.mapper")
public class DatabaseMapperScanConfiguration {
}
