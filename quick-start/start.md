1. mybatis-plus的xml使用
2. 测试类的使用
3. 日志的更新。更新在一个文件
logging:
  config: classpath:logback-plus.xml
  level:
    root: info
#    com.mp.dao: trace
#    com.spm.study: trace
#    com.baomidou.example.mapper: info
  pattern:
    console: '%p%m%n'