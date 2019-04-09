package com.beikai.springboottestdemo.IO;

import java.io.*;


/**
 * 地址工具类
 */
class AddressUtils2 {

    public static String address = "E:\\StorageTest\\abc";

    public static String getAddress(int num) {
        return address + num + ".java";
    }

    public static String getFilePath(int num) {

        if (num > 0) {
            return address + num;
        }

        return address;
    }
}

/**
 * 用户对象
 */
class User2 implements Serializable {

    //实现Serializable  生成序列主键
    private static final long serialVersionUID = 5644866295561055296L;

    private String username;
    private String password;
    private String age;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public User2 setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User2 setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

/**
 * @ClassName Test01
 * @Description TODO
 * @Author Admin
 * @Date 2019/4/9 20:58
 * @Version 1.0
 **/
public class Test01 {
    public static void main(String[] args) {
        // 选择要执行的方法
        switchMethod(9);
    }

    private static void switchMethod(int i) {
        switch (i) {
            case 1:
                // 关于 FileWriter 和 FileReader 流的使用
                AboutFileWriterAndFileReader();
                break;
            case 2:
                //关于 OutputStreamWriter 和 InputStreamReader 的使用
                AboutOutputStreamWriterAndInputStreamReader();
                break;

            case 3:
                //关于 BufferedWriter 和 BufferedReader 的使用
                AboutBufferedWriterAndBufferedReader();
                break;
            case 4:
                //关于 PrintStream 流的使用
                AboutPrintStream();
                break;
            case 5:
                //关于 PrintWriter 的使用
                AboutPrintWriter();
                break;
            case 6:
                //关于对象打印流的使用 注意序列化
                AboutObjectInputStreamAndObjectOutputStream();
                break;
            case 7:
                //关于文件流的操作
                AboutFile();
                break;
            case 8:
                //关于文件对象的一些参数
                AboutFileAttribute();
                break;
            case 9:
                //关于文件夹的一些参数
                AboutFolder();
                break;
            default:
                break;
        }
    }

    /**
     * 对文件夹的操作
     */
    private static void AboutFolder() {
        getFolderName(AddressUtils2.getFilePath(0));
        System.out.println("------------------------------");
        getFolderPath(AddressUtils2.getFilePath(0));
        System.out.println("------------------------------");
        getFolderContent(AddressUtils2.getFilePath(0));
    }

    /**
     * 获取文件夹内容
     *
     * @param filePath
     */
    private static void getFolderContent(String filePath) {
        File file = new File(filePath);

        // 获取文件对象
        File[] files = file.listFiles();

        for (File file1 : files) {
            System.out.println(file1.getAbsolutePath());
            if (file1.isDirectory()) {
                getFolderContent(file1.getAbsolutePath());
            }
        }
    }

    /**
     * 获取文件夹路径
     *
     * @param filePath
     */
    private static void getFolderPath(String filePath) {
        File file = new File(filePath);
        // 获取路径经下文件对象
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getAbsolutePath());
        }
    }

    /**
     * 获取文件名
     *
     * @param filePath
     */
    private static void getFolderName(String filePath) {
        File file = new File(filePath);

        // 获取文件列表信息
        String[] list = file.list();
        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 通过 file 获取文件属性
     * <p>
     * 文件是否存在 ---> true
     * 文件绝对路径 ---> E:\StorageTest\abc\hehe.mp4
     * 文件路径 ---> E:\StorageTest\abc\hehe.mp4
     * 文件父路径 ---> E:\StorageTest\abc
     * 文件名 ---> hehe.mp4
     * 文件大小 ---> 23027559
     * 文件是否是文件 ---> true
     * 文件路径是否是绝对路径 ---> true
     * 文件最后修改时间 ---> 1554301030490
     */
    private static void AboutFileAttribute() {
        // 获取文件属性
        File file = new File(AddressUtils2.getFilePath(0) + "\\hehe.mp4");

        System.out.println("文件是否存在 ---> " + file.exists());
        System.out.println("文件绝对路径 ---> " + file.getAbsolutePath());
        System.out.println("文件路径 ---> " + file.getPath());
        System.out.println("文件父路径 ---> " + file.getParent());
        System.out.println("文件名 ---> " + file.getName());
        System.out.println("文件大小 ---> " + file.length());
        System.out.println("文件是否是文件 ---> " + file.isFile());
        System.out.println("文件路径是否是绝对路径 ---> " + file.isAbsolute());
        System.out.println("文件最后修改时间 ---> " + file.lastModified());

    }

    /**
     * file 是对文件级别的操作, 流是对文件内容的操作
     */
    private static void AboutFile() {
        // 根据地址,创建文件夹
        File file = new File(AddressUtils2.getFilePath(0));
        // 创建文件夹
        file.mkdir();

        // 根据文件地址,创建子文件夹
        File file1 = new File(AddressUtils2.getFilePath(0) + "\\StorageTest");
        file1.mkdir();

        // 根据文件路径,以及文件名,创建文件  第一个参数是路径,第二个参数是文件名
        File file2 = new File(AddressUtils2.getFilePath(0), "abc.txt");
        try {
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 同上
        File file3 = new File(file1, "abc2.txt");
        try {
            file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对象的输入输出流  涉及到序列化
     * <p>
     * 场景描述:
     * 把对象序列化之后,在没有生成serialVersionUID 的前提下,又添加一个字段,在发序列化的时候容易报错:
     * java.io.InvalidClassException: com.beikai.springboottestdemo.IO.Object.User; local class incompatible: stream classdesc serialVersionUID = 5007364209808485824, local class serialVersionUID = -71765399825409547
     * at java.io.ObjectStreamClass.initNonProxy(ObjectStreamClass.java:687)
     * at java.io.ObjectInputStream.readNonProxyDesc(ObjectInputStream.java:1876)
     * at java.io.ObjectInputStream.readClassDesc(ObjectInputStream.java:1745)
     * at java.io.ObjectInputStream.readOrdinaryObject(ObjectInputStream.java:2033)
     * at java.io.ObjectInputStream.readObject0(ObjectInputStream.java:1567)
     * at java.io.ObjectInputStream.readObject(ObjectInputStream.java:427)
     * at com.beikai.springboottestdemo.IO.Object.Test06AboutObjectInputStreamAndObjectOutputStream.reader(Test06AboutObjectInputStreamAndObjectOutputStream.java:42)
     * at com.beikai.springboottestdemo.IO.Object.Test06AboutObjectInputStreamAndObjectOutputStream.main(Test06AboutObjectInputStreamAndObjectOutputStream.java:27)
     * <p>
     * 原因是 如果不生成serialVersionUID 字段, 在把对象写入文件的时候,会自动生成一个serialVersionUID(隐式),在从文件中读取之前如果对象新添加了一个字段,则
     * 在生成对象的时候生成的serialVersionUID与没修改新字段之前是不同的,这就导致在生成对象的时候回生成失败,
     * <p>
     * 解决的办法是保证 serialVersionUID 的值相同,所以可以创建一个固定的 serialVersionUID 值
     */
    private static void AboutObjectInputStreamAndObjectOutputStream() {
        User2 user = new User2().setUsername("张三").setPassword("123456");

        writer(user);

        reader(AddressUtils2.getAddress(6));
    }

    /**
     * 读取文件中的对象
     *
     * @param address
     */
    private static void reader(String address) {

        InputStream inputStream = null;
        // 创建文件读取流对象
        ObjectInputStream objectInputStream = null;
        try {
            inputStream = new FileInputStream(address);
            objectInputStream = new ObjectInputStream(inputStream);

            Object object = objectInputStream.readObject();

            System.out.println(object);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 写入文件中对象信息
     *
     * @param user
     */
    private static void writer(User2 user) {

        // 创建输出流,
        OutputStream outputStream = null;
        // 创建文件写入流对象
        ObjectOutputStream objectOutputStream = null;
        try {
            outputStream = new FileOutputStream(AddressUtils2.getAddress(6), true);
            objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * PrintWriter 的使用方式
     * <p>
     * 以字符为单位的打印流
     */
    private static void AboutPrintWriter() {
        // 创建字符打印流
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new FileWriter("E:\\StorageTest\\abc6.java", true));

            try (
                    FileInputStream fileInputStream = new FileInputStream("dddd");
            ) {

            } catch (FileNotFoundException e) {
                // 打印到这个流中
                e.printStackTrace(printWriter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != printWriter) {
                printWriter.close();
            }
        }
    }

    /**
     * printStream 流的使用 打印流
     */
    private static void AboutPrintStream() {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("E:\\StorageTest\\abc5.java", true);

            PrintStream printStream = new PrintStream(outputStream);
            // 打印到文件中
            printStream.print("打印1");
            printStream.println("打印2");
            printStream.print("打印1");
            // 打印在屏幕上  System.out 系统默认输出设备是显示器
            System.out.println("hello world!");
            // 修改 System.out 打印方向  到流中
            System.setOut(printStream);
            // 打印在文件中
            System.out.println("这一行答应在文件中....");

            // 把异常信息打印在文件中
            try (
                    FileInputStream fileInputStream = new FileInputStream("dddd");
            ) {

            } catch (Exception e) {
                // 把异常信息打印在打印流中
                e.printStackTrace(printStream);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 通过缓冲流读取数据
     * <p>
     * 程序通过 FileReader 从文件中读取数据,存进BufferedReader缓冲流中,这个缓冲流对象的大小是 8092 ,然后程序从缓冲流中读取
     * 程序把从BufferedReader中读取的数据存进BufferedWriter缓冲流中,然后通过 FileWriter 写进文件中
     * <p>
     * 注意:
     * BufferedWriter 中的数据是直接写进文件中的吗?
     * 不是,三种情况下写入:
     * 1. 缓冲满了
     * 2. 调用close()方法
     * 3. 调用 flush()方法
     */
    private static void AboutBufferedWriterAndBufferedReader() {
        String fromPath = "D:\\Windows10\\DevelopmentTools\\IntelliJ_IDEA\\WorkSpaceAboutGitHab" +
                "\\springboot-study-project\\springboot-StorageTest-demo\\src\\main\\java\\com\\beikai\\springboottestdemo\\IO\\Test01AboutFileWriterAndFileReader.java";
        String targetPath = "E:\\StorageTest\\abc4.java";
        String targetPath2 = "E:\\StorageTest\\abc5.java";
        // 文件复制
        reader2(fromPath, targetPath);
        writer2(fromPath, targetPath);

        // 键盘输入写入到文件中
        keyInput(fromPath, targetPath2);
    }

    /**
     * 键盘录入写入文件中
     *
     * @param fromPath    源文件路径
     * @param targetPath2 目标文件路径
     */
    private static void keyInput(String fromPath, String targetPath2) {
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try {
            // 创建写入缓冲流对象
            bufferedWriter = new BufferedWriter(new FileWriter(targetPath2, true));
            // 创建读取缓冲流对象   system.in 为键盘录入
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            // 读取每一行
            String line = bufferedReader.readLine();

            // 循环读取
            while (line.length() > 0) {
                // 写入文件中
                bufferedWriter.write(line);
                // 换行
                bufferedWriter.newLine();
                // 在读取一行
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //关闭
    }

    /**
     * 通过缓冲流写入
     *
     * @param fromPath   源文件路径
     * @param targetPath 目标文件路径
     */
    private static void writer2(String fromPath, String targetPath) {
        Writer writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            // 声明写入流  第二个参数  是否追加
            writer = new FileWriter(targetPath, true);
            // 创建缓冲流对象
            bufferedWriter = new BufferedWriter(writer);

            // 写入数据
            bufferedWriter.write("测试赛的点点滴滴多多大多多多多多");

            // 情况缓冲区 把数据保存到文件中
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    /**
     * 通过缓冲流读取
     *
     * @param fromPath   源文件路径
     * @param targetPath 目标文件路径
     */
    private static void reader2(String fromPath, String targetPath) {
        // 声明对象
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            // 创建文件读取对象
            reader = new FileReader(fromPath);
            // 创建缓冲读取对象
            bufferedReader = new BufferedReader(reader);
            // 每次读取一行
            String line = bufferedReader.readLine();
            // 循环读取
            while (line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /**
     * 使用转换流方式读写数据
     * 当文本编码格式与环境编码格式不同时使用
     * OutputStreamWriter 和  OutputStream   以及   InputStreamReader  和   InputStream
     * 转化流采用适配器设计模式
     * <p>
     * 设计模式 "
     * 别人总结的一套解决方案,并被大多数人接受
     * <p>
     * 适配器 :
     * 不同之间转换的工具
     */
    private static void AboutOutputStreamWriterAndInputStreamReader() {
        String fromPath = "D:\\Windows10\\DevelopmentTools\\IntelliJ_IDEA\\WorkSpaceAboutGitHab" +
                "\\springboot-study-project\\springboot-StorageTest-demo\\src\\main\\java\\com\\beikai\\springboottestdemo\\IO\\Test01AboutFileWriterAndFileReader.java";
        String targetPath = "E:\\StorageTest\\abc3.java";

        reader(fromPath, targetPath);

        writer(fromPath, targetPath);
    }

    /**
     * 写入转换流
     *
     * @param fromPath   源文件路径
     * @param targetPath 目标文件路径
     */
    private static void writer(String fromPath, String targetPath) {
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            // 创建一个字节写入流  第一个参数是 目标文件, 第二个参数是 是否叠加
            outputStream = new FileOutputStream(targetPath, true);
            // 创建一个转换流  第一个参数是 输入流对象  第二个参数是 目标文件编码格式
            outputStreamWriter = new OutputStreamWriter(outputStream, "utf-8");

            //写入
            outputStreamWriter.write("创建一个转换流  第一个参数是 输入流对象  第二个参数是 目标文件编码格式");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取转换流
     *
     * @param fromPath   源文件路径
     * @param targetPath 目标文件路径
     */
    private static void reader(String fromPath, String targetPath) {
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            // 创建一个字节读取流
            inputStream = new FileInputStream(fromPath);
            // 创建一个转换流  第一个参数是  字节读取流, 第二个参数是 源文件编码格式
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");

            // 一次读取一个字节
            int cc = inputStreamReader.read();
            while (cc != -1) {
                // 输出  也可以在这里写入
                System.out.print((char) cc);
                cc = inputStreamReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 测试 字符输入流 和 字符输出流
     * FileReader  和  FileWriter
     * <p>
     * 缺点:
     * filereader / fileWriter 只能写与当前环境编码兼容的文本文件
     * 如果文本文件与当前环境编码不兼容,使用InputStreamReader/OutputStreamWriter  转换流
     * <p>
     * 一般使用filereader 读取 与 当前环境编码一致的文件,如果不一致,会导致乱码,而且只能读取纯文本文件
     * <p>
     * filewriter 保存数据的时候, 同上一样,需要文件编码 与 环境编码格式相同
     */
    private static void AboutFileWriterAndFileReader() {
        String fromPath = "D:\\Windows10\\DevelopmentTools\\IntelliJ_IDEA\\WorkSpaceAboutGitHab" +
                "\\springboot-study-project\\springboot-StorageTest-demo\\src\\main\\java\\com\\beikai\\springboottestdemo\\IO\\Test01AboutFileWriterAndFileReader.java";
        String targetPath = "E:\\StorageTest\\abc.java";
        String targetPath2 = "E:\\StorageTest\\abc2.java";

        copyFileOneChar(fromPath, targetPath);
        copyFileOneArray(fromPath, targetPath2);
    }

    /**
     * 拷贝文件
     * 文本文件复制,一次复制一个字符,异常处理,会自动关闭流
     *
     * @param fromPath   源文件路径
     * @param targetPath 目标文件路径
     */
    public static void copyFileOneChar(String fromPath, String targetPath) {

        /**
         * 流对象放进 try(){} 的()中, 创建的资源会自动释放
         */
        try (// 创建字符读取流
             FileReader fileReader = new FileReader(fromPath);
             // 创建字符写入流
             FileWriter fileWriter = new FileWriter(targetPath);
        ) {
            // 设置每次读取的字符
            int cc = fileReader.read();
            // 如果不为 -1 说明还没读完
            while (cc != -1) {
                // 调用写入流写入字符
                fileWriter.write(cc);
                // 重新读取
                cc = fileReader.read();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拷贝文件,一次拷贝一个数组
     *
     * @param fromPath   源文件目录
     * @param targetPath 目标文件路径
     */
    public static void copyFileOneArray(String fromPath, String targetPath) {

        // 声明 字符读取流和字符写入流对象
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {

            fileReader = new FileReader(fromPath);
            fileWriter = new FileWriter(targetPath);
            // 创建数组,用于存放读取的数据
            char[] c = new char[1024];
            // 获取当前数组中读取的数量
            int len = fileReader.read(c);

            while (len != -1) {
                // 写入流 根据数组写入数据  第一个参数是 数组,第二个参数是 读取的起始位置, 第三个参数是 读取的终止位置
                fileWriter.write(c, 0, len);
                // 再次读取
                len = fileReader.read(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 最终 关闭流
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
