package com.github.qia;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Import;

@Configurable
@Import(SpringWebMvcConfigurer.class)
public class SpringConfiguration {

}
