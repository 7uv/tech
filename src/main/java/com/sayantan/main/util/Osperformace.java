/**
 * 
 */
package com.sayantan.main.util;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;
import com.sun.jna.win32.W32APIOptions;
import com.sun.management.OperatingSystemMXBean;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.hardware.CentralProcessor.TickType;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.lang.management.ManagementFactory;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author    Sayantan Ghosh
 * @copyright Sayantan Ghosh
 */
@SuppressWarnings("restriction")
public class Osperformace {

	/**
	 * 
	 */
	public Osperformace() {
		// TODO Auto-generated constructor stub
		// Hash a password for the first time
		

		// gensalt's log_rounds parameter determines the complexity
		// the work factor is 2**log_rounds, and the default is 10
		//String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));

		// Check that an unencrypted password matches one that has
		// previously been hashed
		//if (BCrypt.checkpw(candidate, hashed))
		//System.out.println("It matches");
		//else i
		//System.out.println("It does not match");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Options: ERROR > WARN > INFO > DEBUG > TRACE
        //System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "INFO");
        Logger LOG = LoggerFactory.getLogger(Osperformace.class);
		LOG.info("*** START OF REPORT ***");
		// TODO Auto-generated method stub
		OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
		// What % CPU load this current JVM is taking, from 0.0-1.0
		System.out.println("# CPU load: " + rounding((double) osBean.getProcessCpuLoad()) + " %");

		// What % load the overall system is at, from 0.0-1.0
		System.out.println("# System load: " + rounding((double) osBean.getSystemCpuLoad()) + " %");

		// Returns the total amount of swap space in bytes.
		System.out.println(
				"# Total Swap space: " + rounding((double) osBean.getTotalSwapSpaceSize() / Math.pow(10, 9)) + " Gb");

		// Returns the amount of free swap space in bytes.
		System.out.println(
				"# Free Swap space: " + rounding((double) osBean.getFreeSwapSpaceSize() / Math.pow(10, 9)) + " Gb");

		// Returns the amount of virtual memory that is guaranteed to be
		// available to the running process in bytes, or -1 if this operation is
		// not supported.
		System.out.println("# Total Virtual memory: "
				+ rounding((double) osBean.getCommittedVirtualMemorySize() / Math.pow(10, 9)) + " Gb");

		// Returns the total amount of physical memory in bytes.
		System.out.println("# Total physical memory: "
				+ rounding((double) osBean.getTotalPhysicalMemorySize() / Math.pow(10, 9)) + " Gb");

		// Returns the amount of free physical memory in bytes.
		System.out.println("# Free physical memory: "
				+ rounding((double) osBean.getFreePhysicalMemorySize() / Math.pow(10, 9)) + " Gb");

		// Returns the "recent cpu usage" for the Java Virtual Machine process
		System.out.println(
				"# Recent CPU usage for monitor process: " + rounding((double) osBean.getProcessCpuLoad()) + " %");

		// CPU Usage
		System.out.println("# CPU Time usage for monitor process: "
				+ rounding((double) osBean.getProcessCpuTime() / Math.pow(10, 6)) + " sec");
		System.out.println("# System CPU Usage: " + rounding((double) osBean.getSystemCpuLoad()) + " Bytes");

		// OS Details
		System.out.println("# Underlying OS: " + System.getProperty("os.name"));
		System.out.println("# OS Arch: " + System.getProperty("os.arch"));
		System.out.println("# OS Version: " + System.getProperty("os.version"));
		System.out.println("# JRE Version: " + System.getProperty("java.runtime.version"));
		System.out.println("# User name: " + System.getProperty("user.name"));
		System.out.println("# User home directory: " + System.getProperty("user.dir"));

		// System.getProperties().list(System.out);
		if (System.getProperty("os.name").contains("Win")) {
			Kernel32 kernel32 = (Kernel32) Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
			Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();

			WinNT.HANDLE snapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));
			try {
				while (kernel32.Process32Next(snapshot, processEntry)) {
					System.out.println(processEntry.th32ProcessID + "\t" + Native.toString(processEntry.szExeFile));
				}
			} finally {
				kernel32.CloseHandle(snapshot);
			}
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.out.println("Asjkdfg");
		} else if (System.getProperty("os.name").contains("Mac")) {

		} else {
			System.out.println("Unknown OS: " + System.getProperty("os.name"));
		}

        LOG.info("Initializing System...");
        SystemInfo si = new SystemInfo();

        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        System.out.println(os);

        printProcessor(hal.getProcessor());

        LOG.info("Checking Memory...");
        printMemory(hal.getMemory());

        LOG.info("Checking CPU...");
        printCpu(hal.getProcessor());

        LOG.info("Checking Processes...");
        printProcesses(os, hal.getMemory());

        LOG.info("Checking Sensors...");
        printSensors(hal.getSensors());

        LOG.info("Checking Power sources...");
        printPowerSources(hal.getPowerSources());

        LOG.info("Checking Disks...");
        printDisks(hal.getDiskStores());

        LOG.info("Checking File System...");
        printFileSystem(os.getFileSystem());

        LOG.info("Checking Network interfaces...");
        printNetworkInterfaces(hal.getNetworkIFs());

        // hardware: displays
        LOG.info("Checking Displays...");
        printDisplays(hal.getDisplays());

        // hardware: USB devices
        LOG.info("Checking USB Devices...");
        printUsbDevices(hal.getUsbDevices(true));
        
        // End of report
        LOG.info("*** END OF REPORT ***");
        
        // Password encryptor 
        String password = "Dell@123";
        int salt = (int)Math.floor(Math.random() * 12) + 1;
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(salt));
		LOG.info(salt + "hashed " + password + "= " + hashed);
		if(BCrypt.checkpw(password, hashed)){
				LOG.info("matches");
		}
    }

    private static void printProcessor(CentralProcessor processor) {
        System.out.println(processor);
        System.out.println(" " + processor.getPhysicalProcessorCount() + " physical CPU(s)");
        System.out.println(" " + processor.getLogicalProcessorCount() + " logical CPU(s)");

        System.out.println("Identifier: " + processor.getIdentifier());
        System.out.println("Serial Num: " + processor.getSystemSerialNumber());
    }

    private static void printMemory(GlobalMemory memory) {
        System.out.println("Memory: " + FormatUtil.formatBytes(memory.getAvailable()) + "/"
                + FormatUtil.formatBytes(memory.getTotal()));
        System.out.println("Swap used: " + FormatUtil.formatBytes(memory.getSwapUsed()) + "/"
                + FormatUtil.formatBytes(memory.getSwapTotal()));
    }

    private static void printCpu(CentralProcessor processor) {
        System.out.println("Uptime: " + FormatUtil.formatElapsedSecs(processor.getSystemUptime()));

        long[] prevTicks = processor.getSystemCpuLoadTicks();
        System.out.println("CPU, IOWait, and IRQ ticks @ 0 sec:" + Arrays.toString(prevTicks));
        // Wait a second...
        Util.sleep(1000);
        long[] ticks = processor.getSystemCpuLoadTicks();
        System.out.println("CPU, IOWait, and IRQ ticks @ 1 sec:" + Arrays.toString(ticks));
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long sys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq;

        System.out.format(
                "User: %.1f%% Nice: %.1f%% System: %.1f%% Idle: %.1f%% IOwait: %.1f%% IRQ: %.1f%% SoftIRQ: %.1f%%%n",
                100d * user / totalCpu, 100d * nice / totalCpu, 100d * sys / totalCpu, 100d * idle / totalCpu,
                100d * iowait / totalCpu, 100d * irq / totalCpu, 100d * softirq / totalCpu);
        System.out.format("CPU load: %.1f%% (counting ticks)%n", processor.getSystemCpuLoadBetweenTicks() * 100);
        System.out.format("CPU load: %.1f%% (OS MXBean)%n", processor.getSystemCpuLoad() * 100);
        double[] loadAverage = processor.getSystemLoadAverage(3);
        System.out.println("CPU load averages:" + (loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
                + (loadAverage[1] < 0 ? " N/A" : String.format(" %.2f", loadAverage[1]))
                + (loadAverage[2] < 0 ? " N/A" : String.format(" %.2f", loadAverage[2])));
        // per core CPU
        StringBuilder procCpu = new StringBuilder("CPU load per processor:");
        double[] load = processor.getProcessorCpuLoadBetweenTicks();
        for (double avg : load) {
            procCpu.append(String.format(" %.1f%%", avg * 100));
        }
        System.out.println(procCpu.toString());
    }

    private static void printProcesses(OperatingSystem os, GlobalMemory memory) {
        System.out.println("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());
        // Sort by highest CPU
        List<OSProcess> procs = Arrays.asList(os.getProcesses(30, ProcessSort.CPU));

        System.out.println("   PID  %CPU %MEM       VSZ       RSS Name");
        for (int i = 0; i < procs.size() && i < 30; i++) {
            OSProcess p = procs.get(i);
            System.out.format(" %5d %5.1f %4.1f %9s %9s %s%n", p.getProcessID(),
                    100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
                    100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
                    FormatUtil.formatBytes(p.getResidentSetSize()), p.getName());
        }
    }

    private static void printSensors(Sensors sensors) {
        System.out.println("Sensors:");
        System.out.format(" CPU Temperature: %.1f°C%n", sensors.getCpuTemperature());
        System.out.println(" Fan Speeds: " + Arrays.toString(sensors.getFanSpeeds()));
        System.out.format(" CPU Voltage: %.1fV%n", sensors.getCpuVoltage());
    }

    private static void printPowerSources(PowerSource[] powerSources) {
        StringBuilder sb = new StringBuilder("Power: ");
        if (powerSources.length == 0) {
            sb.append("Unknown");
        } else {
            double timeRemaining = powerSources[0].getTimeRemaining();
            if (timeRemaining < -1d) {
                sb.append("Charging");
            } else if (timeRemaining < 0d) {
                sb.append("Calculating time remaining");
            } else {
                sb.append(String.format("%d:%02d remaining", (int) (timeRemaining / 3600),
                        (int) (timeRemaining / 60) % 60));
            }
        }
        for (PowerSource pSource : powerSources) {
            sb.append(String.format("%n %s @ %.1f%%", pSource.getName(), pSource.getRemainingCapacity() * 100d));
        }
        System.out.println(sb.toString());
    }

    private static void printDisks(HWDiskStore[] diskStores) {
        System.out.println("Disks:");
        for (HWDiskStore disk : diskStores) {
            boolean readwrite = disk.getReads() > 0 || disk.getWrites() > 0;
            System.out.format(" %s: (model: %s - S/N: %s) size: %s, reads: %s (%s), writes: %s (%s), xfer: %s ms%n",
                    disk.getName(), disk.getModel(), disk.getSerial(),
                    disk.getSize() > 0 ? FormatUtil.formatBytesDecimal(disk.getSize()) : "?",
                    readwrite ? disk.getReads() : "?", readwrite ? FormatUtil.formatBytes(disk.getReadBytes()) : "?",
                    readwrite ? disk.getWrites() : "?", readwrite ? FormatUtil.formatBytes(disk.getWriteBytes()) : "?",
                    readwrite ? disk.getTransferTime() : "?");
            HWPartition[] partitions = disk.getPartitions();
            if (partitions == null) {
                // TODO Remove when all OS's implemented
                continue;
            }
            for (HWPartition part : partitions) {
                System.out.format(" |-- %s: %s (%s) Maj:Min=%d:%d, size: %s%s%n", part.getIdentification(),
                        part.getName(), part.getType(), part.getMajor(), part.getMinor(),
                        FormatUtil.formatBytesDecimal(part.getSize()),
                        part.getMountPoint().isEmpty() ? "" : " @ " + part.getMountPoint());
            }
        }
    }

    private static void printFileSystem(FileSystem fileSystem) {
        System.out.println("File System:");

        System.out.format(" File Descriptors: %d/%d%n", fileSystem.getOpenFileDescriptors(),
                fileSystem.getMaxFileDescriptors());

        OSFileStore[] fsArray = fileSystem.getFileStores();
        for (OSFileStore fs : fsArray) {
            long usable = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            System.out.format(" %s (%s) [%s] %s of %s free (%.1f%%) is %s and is mounted at %s%n", fs.getName(),
                    fs.getDescription().isEmpty() ? "file system" : fs.getDescription(), fs.getType(),
                    FormatUtil.formatBytes(usable), FormatUtil.formatBytes(fs.getTotalSpace()), 100d * usable / total,
                    fs.getVolume(), fs.getMount());
        }
    }

    private static void printNetworkInterfaces(NetworkIF[] networkIFs) {
        System.out.println("Network interfaces:");
        for (NetworkIF net : networkIFs) {
            System.out.format(" Name: %s (%s)%n", net.getName(), net.getDisplayName());
            System.out.format("   MAC Address: %s %n", net.getMacaddr());
            System.out.format("   MTU: %s, Speed: %s %n", net.getMTU(), FormatUtil.formatValue(net.getSpeed(), "bps"));
            System.out.format("   IPv4: %s %n", Arrays.toString(net.getIPv4addr()));
            System.out.format("   IPv6: %s %n", Arrays.toString(net.getIPv6addr()));
            boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
                    || net.getPacketsSent() > 0;
            System.out.format("   Traffic: received %s/%s; transmitted %s/%s %n",
                    hasData ? net.getPacketsRecv() + " packets" : "?",
                    hasData ? FormatUtil.formatBytes(net.getBytesRecv()) : "?",
                    hasData ? net.getPacketsSent() + " packets" : "?",
                    hasData ? FormatUtil.formatBytes(net.getBytesSent()) : "?");
        }
    }

    private static void printDisplays(Display[] displays) {
        System.out.println("Displays:");
        int i = 0;
        for (Display display : displays) {
            System.out.println(" Display " + i + ":");
            System.out.println(display.toString());
            i++;
        }
    }

    private static void printUsbDevices(UsbDevice[] usbDevices) {
        System.out.println("USB Devices:");
        for (UsbDevice usbDevice : usbDevices) {
            System.out.println(usbDevice.toString());
        }
    }

	public static BigDecimal rounding(double d) {
		BigDecimal a = new BigDecimal(d);
		BigDecimal roundoff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return roundoff;
	}
}
