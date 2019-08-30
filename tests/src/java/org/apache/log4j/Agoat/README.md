#
    Log4J将写日志功能抽象成七个核心类或者接口：
    Logger   Logger继承Category，Category也是一种日志类
    LoggerRepository  LoggerRepository是Logger实例的容器
    Level            Level对日志级别的抽象
    LoggingEvent     LoggingEvent是对一次日志记录过程中所能取到信息的抽象
    Appender         Appender是对记录日志形式的抽象
    Layout          Layout是对日志行格式的抽象
    ObjectRender   ObjectRender是对日志实例的解析接口，它们主要提供了一种扩展支持。