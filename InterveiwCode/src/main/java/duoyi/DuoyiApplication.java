package duoyi;

import java.util.HashMap;
import java.util.Map;

public class DuoyiApplication {

    // 服务类型枚举
    enum ServiceType {
        MYSQL("mysql"),
        NGINX("nginx"),
        MONGODB("mongodb");

        private final String prefix;

        ServiceType(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return prefix;
        }
    }

    // 配置项类
    static class ConfigItem {
        private final String key;
        private final String value;

        public ConfigItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    // 服务配置类
    static class ServiceConfig {
        private final ServiceType serviceType;
        private final Map<String, ConfigItem> configItems;

        public ServiceConfig(ServiceType serviceType) {
            this.serviceType = serviceType;
            this.configItems = new HashMap<>();
        }

        public void addConfigItem(String key, String value) {
            configItems.put(key, new ConfigItem(key, value));
        }

        public ServiceType getServiceType() {
            return serviceType;
        }

        public Map<String, ConfigItem> getConfigItems() {
            return configItems;
        }
    }

    // 配置打印机接口
    interface ConfigPrinter {
        void printConfig(ServiceConfig serviceConfig);
    }

    // 默认配置打印机实现
    static class DefaultConfigPrinter implements ConfigPrinter {
        @Override
        public void printConfig(ServiceConfig serviceConfig) {
            System.out.println("=== " + serviceConfig.getServiceType().name() + " Configurations ===");
            for (ConfigItem item : serviceConfig.getConfigItems().values()) {
                System.out.println(serviceConfig.getServiceType().getPrefix() + ": " +
                        item.getKey() + " = " + item.getValue());
            }
            System.out.println();
        }
    }

    // 配置管理器
    static class ConfigManager {
        private final Map<ServiceType, ServiceConfig> serviceConfigs;
        private final ConfigPrinter configPrinter;

        public ConfigManager(ConfigPrinter configPrinter) {
            this.serviceConfigs = new HashMap<>();
            this.configPrinter = configPrinter;
        }
        public void addServiceConfig(ServiceType serviceType) {
            if (!serviceConfigs.containsKey(serviceType)) {
                serviceConfigs.put(serviceType, new ServiceConfig(serviceType));
            }
        }

        public void addConfigItem(ServiceType serviceType, String key, String value) {
            if (!serviceConfigs.containsKey(serviceType)) {
                addServiceConfig(serviceType);
            }
            serviceConfigs.get(serviceType).addConfigItem(key, value);
        }

        public void printAllConfigs() {
            for (ServiceConfig config : serviceConfigs.values()) {
                configPrinter.printConfig(config);
            }
        }

        public void printConfig(ServiceType serviceType) {
            if (serviceConfigs.containsKey(serviceType)) {
                configPrinter.printConfig(serviceConfigs.get(serviceType));
            }
        }
    }

    // 使用示例
        public static void main(String[] args) {
            // 创建配置管理器
            ConfigManager configManager = new ConfigManager(new DefaultConfigPrinter());

            // 添加MySQL配置
            configManager.addConfigItem(ServiceType.MYSQL, "timeout", "60");
            configManager.addConfigItem(ServiceType.MYSQL, "max_connections", "100");

            // 添加Nginx配置
            configManager.addConfigItem(ServiceType.NGINX, "path", "/sadsa/sadas/sd.jj");
            configManager.addConfigItem(ServiceType.NGINX, "worker_processes", "4");

            // 添加MongoDB配置（演示扩展性）
            configManager.addConfigItem(ServiceType.MONGODB, "port", "27017");
            configManager.addConfigItem(ServiceType.MONGODB, "dbpath", "/data/db");

            // 打印所有配置
            configManager.printAllConfigs();

            // 也可以单独打印某个服务的配置
            System.out.println("=== Only MySQL Config ===");
            configManager.printConfig(ServiceType.MYSQL);
        }
}
