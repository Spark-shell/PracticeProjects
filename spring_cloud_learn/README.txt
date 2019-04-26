springCloud 学习DEMO笔记
    Maven命令含义
        1.maven的用途
            maven是一个项目构建和管理的工具，提供了帮助管理 构建、文档、报告、依赖、scms、发布、分发的方法。可以方便的编译代码、进行依赖管理、管理二进制库等等。
            maven的好处在于可以将项目过程规范化、自动化、高效化以及强大的可扩展性
            利用maven自身及其插件还可以获得代码检查报告、单元测试覆盖率、实现持续集成等等。
        2.maven命令含义
            clearn :    删除项目路径下的target文件，但是不会删除本地的maven仓库已经生成的jar文件
            install:    在package的基础上，会在本地maven仓库生成jar文件，供其他项目使用
            compile:    编译命令，会在项目路径下生成一个target目录，目录包含一个classes文件夹，里面是class文件和字节码文件
            package:    在compile的基础上生成jar、war文件
            test:
            site:
            deploy:
            validate:

        3.<build></build>
            在<build><pluginManagement>与<plugins>并列，两者之间的关系类似于<dependencyManagement>与<dependencies>之间的关系。
                    <pluginManagement>中也配置<plugin>，其配置参数与<plugins>中的<plugin>完全一致。只是，
                    <pluginManagement>往往出现在父项目中，其中配置的<plugin>往往通用于子项目。
                    子项目中只要在<plugins>中以<plugin>声明该插件，该插件的具体配置参数则继承自父项目中<pluginManagement>
                    对该插件的配置，从而避免在子项目中进行重复配置
            示例：
                <build>
                        <!--给出构建过程中需哟啊用到的插件
                                groupId
                                artifactId
                                version
                                extensions，     是否加载该插件的扩展，默认false
                                inherited，      该插件的configuration中的配置是否可以被（继承该POM的其他Maven项目）继承，默认true
                                configuration，  该插件所需要的特殊配置，在父子项目之间可以覆盖或合并
                                dependencies，   该插件所特有的依赖类库
                                executions，     该插件的某个goal（一个插件中可能包含多个goal）的执行方式。一个execution有如下设置：
                                id，             唯一标识
                                goals，          要执行的插件的goal（可以有多个），如<goal>run</goal>
                                phase，          插件的goal要嵌入到Maven的phase中执行，如verify
                                inherited，      该execution是否可被子项目继承
                                configuration，  该execution的其他配置参数
                        -->
                        <plugins>
                            <plugin>
                                <groupId>org.asciidoctor</groupId>
                                <artifactId>asciidoctor-maven-plugin</artifactId>
                                <version>1.5.3</version>
                                <!--<extensions>是执行构建过程中可能用到的其他工具，在执行构建的过程中被加入到classpath中。
                                    也可以通过<extensions>激活构建插件，从而改变构建的过程。
                                    通常，通过<extensions>给出通用插件的一个具体实现，用于构建过程。
                                    <extensions>
                                是否加载该插件的扩展，默认false-->
                                <executions>
                                    <execution>
                                        <id>generate-docs</id>
                                        <phase>prepare-package</phase>
                                        <goals>
                                            <goal>process-asciidoc</goal>
                                        </goals>
                                        <configuration>
                                            <backend>html</backend>
                                            <doctype>book</doctype>
                                        </configuration>
                                    </execution>
                                </executions>
                                <dependencies>
                                    <dependency>
                                        <groupId>org.springframework.restdocs</groupId>
                                        <artifactId>spring-restdocs-asciidoctor</artifactId>
                                        <version>${spring-restdocs.version}</version>
                                    </dependency>
                                </dependencies>
                            </plugin>
                            <plugin>
                                <groupId>org.springframework.boot</groupId>
                                <artifactId>spring-boot-maven-plugin</artifactId>
                            </plugin>
                        </plugins>
                    </build>