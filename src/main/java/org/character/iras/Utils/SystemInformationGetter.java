package org.character.iras.Utils;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import java.util.HashMap;
import java.util.Map;

public class SystemInformationGetter {
    public SystemInformationGetter() {
    }

    public Map<String, String> getInformation(){
        Map<String, String> result = new HashMap<>();
        result.put("Java Version: ", System.getProperty("java.version"));
        result.put("Host OS:", System.getProperty("os.name"));
        result.put("OS Version:", System.getProperty("os.version"));
        result.put("OS Arch:", System.getProperty("os.arch"));
        SystemInfo systemInfo = new SystemInfo();
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        result.put("CPU: ", processor.getPhysicalProcessorCount() + " core(s) " + processor.getLogicalProcessorCount() + " threads");
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        result.put("Memory: ", memory.getTotal() / 10e8 + " GB");
        result.put("Memory Available: ", memory.getAvailable() / 10e8 + " GB");
        return result;
    }
}
