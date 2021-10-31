package com.jechoi.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {"com.jechoi.core"}, // 여러개 지정 가능
        basePackageClasses = AutoAppConfig.class, // 해당 클래스가 존재하는 패키지부터 뒤짐.
        // 없으면, 자신이 존재하는 패키지부터 뒤짐.
        //제외, @Configuration붙은거 제외 (내부에 @Component가 있어서 스캔 대상임. 이미 생성했던 수동 의존관계 등록 파일을 제외한거임.)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
