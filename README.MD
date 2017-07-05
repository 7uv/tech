Aggregator: Assortment of technologies including Arquillian
========================
    Author: Sayantan Ghosh 
    Level: Middleware
    Technologies: CDI, JSF, JPA, EJB, JPA, JAX-RS, BV
    Summary: A middleware portal.
    Target Project: WildFly
    Source: <https://github.com/wildfly/quickstart/>

What is it?
-----------

This is your project! It is a sample, deployable Maven 3 project to help you get your foot in the door developing with Java EE 7 on JBoss WildFly.

This project is setup to allow you to create a compliant Java EE 7 application using JSF 2.2, CDI 1.1, EJB 3.3, JPA 2.1 and Bean Validation 1.1. It includes a persistence unit and some sample persistence and transaction code to introduce you to database access in enterprise Java.

There is a tutorial for this quickstart in the [Getting Started Developing Applications Guide](https://github.com/wildfly/quickstart/guide/aggregator/).

System requirements
-------------------

All you need to build this project is Java 7.0 (Java SDK 1.7) or better, Maven 3.1 or better.

The application this project produces is designed to be run on JBoss WildFly.

 
Configure Maven
---------------

If you have not yet done so, you must [Configure Maven](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN.md) before testing the quickstarts.


Start JBoss WildFly with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

    
    For Linux:   JBOSS_HOME/bin/standalone.sh
    For Windows: JBOSS_HOME\bin\standalone.bat

 
Build and Deploy the Quickstart
-------------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](https://github.com/jboss-developer/jboss-eap-quickstarts#build-and-deploy-the-quickstarts) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

    
    mvn clean package wildfly:deploy

4. This will deploy `target/aggregator.war` to the running instance of the server.

Install local repositories
--------------------------
_NOTE: Make sure that you are in the project root directory in terminal._

    mvn install:install-file -Dfile=src/main/webapp/WEB-INF/lib/BootsFaces.jar -DgroupId=net -DartifactId=bootsfaces -Dversion=0.9.3 -Dpackaging=jar



Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/aggregator/>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

    
    mvn wildfly:undeploy


Run the Arquillian Tests 
-------------------------

This quickstart provides Arquillian tests. By default, these tests are configured to be skipped as Arquillian tests require the use of a container. 

_NOTE: The following commands assume you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Run the Arquillian Tests](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/RUN_ARQUILLIAN_TESTS.md) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type the following command to run the test goal with the following profile activated:
    
    
    mvn clean test -Parq-wildfly-remote


Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------
You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_JBDS.md) 


Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc
    
    
Database change
---------------
Following measures can be used to perform changes in a database.

	alter table bandwidth alter column bandwidth type numeric(10,2) using bandwidth::numeric;
	select entity, location, sum(bwg) from (select entity, location, sum(bandwidth) as "bwg"
    	from bandwidth b1
    	group by b1.entity, b1.location) T1 
    	group by entity, location, bwg;

	
Git changes
-----------
Following commands may be used to track file changes

	git log --follow filename (/dirname)
	git log -p filename
	gitk --all --first-parent --remotes --reflog --author-date-order -- filename

    git status -v
    git diff --cached
    git update-index --assume-unchanged src/main/webapp/WEB-INF/web.xml
	
	git init --bare
	git init
	
	git clone ssh://john@example.com/path/to/my-project.git
	cd my-project
	
	git add --all .
	git log --pretty=format:"%h %s" HEAD~3..HEAD
	
	git commit -m "message"
	
	git commit --amend
	git rebase -i HEAD~4
	
	git push origin master
	git push bitb master
	
	git stash
	git stash list
	git stash pop
	git stash apply
	git stash apply --index
	
	git stash show -p stash@{0} | git apply -R
	git config --global alias.stash-unapply '!git stash show -p | git apply -R'
	git stash-unapply
	git stash branch testchanges
	git stash drop
	
	git branch dirty
	git checkout dirty
	
	git stash drop
	git reset --hard
	git clean -xfd -n
	git clean -xfd
	git pull origin master
	git reset --hard

psql commands
-------------
Following commands may be used to display postgres details	
	
	\l
	\d
	\dt+
	\c reports
	
configure datasources
---------------------

    module add --name=com.mysql --resources=/home/me/.m2/repository/mysql/mysql-connector-java/5.1.31/mysql-connector-java-5.1.31.jar  --dependencies=javax.api,javax.transaction.api
    reload
    shutdown
    connect
    
    # connect to custom ip:port
    connect 127.0.0.1:10993
    
    # undeploy resources
    undeploy aggregator.war
    
    #install Mysql jdbc driver
    module add --name=com.mysql --resources=/home/me/.m2/repository/mysql/mysql-connector-java/5.1.31/mysql-connector-java-5.1.31.jar --dependencies=javax.api,javax.transaction.api
    
    # install Mysql datasource
    /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-class-name=com.mysql.jdbc.Driver)
    
    # install Postgres jdbc driver
    module add --name=org.postgresql --resources=/home/me/.m2/repository/org/postgresql/postgresql/9.4.1210/postgresql-9.4.1210.jar --dependencies=javax.api,javax.transaction.api
    
    # install Postgres datasource
    /subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)
    
    # define max-pool size for Postgres subsystem
    # for other variables, change in the xml file
    /subsystem=datasources/data-source=PostgresDS/:write-attribute(name=max-pool-size,value=500)
    
    # reload subsystem
    reload
    shutdown
    connect
    
    # deploy war
    deploy --force /home/me/Documents/Java_Projects/Projects/aggregator/target/aggregator.war
    
    # shutdown system
    shutdown
at this point, best way to enable individual datasources is by configuring them via web console. 
In any case, the __*standalone.xml*__ *file* should have the following entry: 
    
    <subsystem xmlns="urn:jboss:domain:datasources:4.0">
        <datasources>
            <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" use-java-context="true">
                <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
                <driver>h2</driver>
                <security>
                    <user-name>sa</user-name>
                    <password>sa</password>
                </security>
            </datasource>
            <datasource jndi-name="java:jboss/datasources/PostgresDS" pool-name="PostgresDS">
                <connection-url>jdbc:postgresql://localhost:5434/reports</connection-url>
                <driver>postgresql</driver>
                <pool>
                    <min-pool-size>20</min-pool-size>
                    <max-pool-size>500</max-pool-size>
                </pool>
                <security>
                    <user-name>adminJHD5464</user-name>
                    <password>${VAULT::datasource.PostgresDS::password::1}</password>
                </security>
                <validation>
                <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
                <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
            </validation>
            </datasource>
            <datasource jta="true" jndi-name="java:jboss/datasources/PostgresDSPrd" pool-name="PostgresDSPrd" enabled="true" use-ccm="true">
                <connection-url>jdbc:postgresql://xxx.xxx.xxx.xxx:5434/reports</connection-url>
                <driver-class>org.postgresql.Driver</driver-class>
                <driver>postgresql</driver>
                <security>
                    <user-name>adminJHD5464</user-name>
                    <password>A564SFDa654dsf</password>
                </security>
                <validation>
                    <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLValidConnectionChecker"/>
                    <background-validation>true</background-validation>
                    <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.postgres.PostgreSQLExceptionSorter"/>
                </validation>
            </datasource>
            <xa-datasource jndi-name="java:jboss/datasources/MySQLDS" pool-name="MySQLDS" enabled="true" use-ccm="true">
                <xa-datasource-property name="ServerName">
                    localhost
                </xa-datasource-property>
                <xa-datasource-property name="DatabaseName">
                    reports
                </xa-datasource-property>
                <driver>mysql</driver>
                <security>
                    <user-name>adminJHD5464</user-name>
                    <password>A564SFDa654dsf</password>
                </security>
                <validation>
                    <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                    <background-validation>true</background-validation>
                    <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                </validation>
            </xa-datasource>
            <xa-datasource jndi-name="java:jboss/datasources/MySQLDSBkp" pool-name="MySQLDSBkp" enabled="true" use-ccm="true">
                <xa-datasource-property name="ServerName">
                    xxx.xxx.xxx.xxx
                </xa-datasource-property>
                <xa-datasource-property name="DatabaseName">
                    reports
                </xa-datasource-property>
                <driver>mysql</driver>
                <security>
                    <user-name>adminJHD5464</user-name>
                    <password>A564SFDa654dsf</password>
                </security>
                <validation>
                    <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                    <background-validation>true</background-validation>
                    <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                </validation>
            </xa-datasource>
            <drivers>
                <driver name="h2" module="com.h2database.h2">
                    <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                </driver>
                <driver name="postgresql" module="org.postgresql">
                    <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
                </driver>
                <driver name="mysql" module="com.mysql">
                    <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
                </driver>
            </drivers>
        </datasources>
    </subsystem>

and the modules should be present as

    [root@sayantan ~]# tree /usr/local/sayantan/EAP-7.0.0/modules/org/
    /usr/local/sayantan/EAP-7.0.0/modules/org/
    └── postgresql
        └── main
            ├── module.xml
            └── postgresql-9.4.1210.jar
    
    2 directories, 2 files
    
    [root@sayantan ~]# nano module.xml
    <?xml version="1.0" encoding="UTF-8" standalone="no"?>
    <module xmlns="urn:jboss:module:1.0" name="org.postgresql">
        <resources>
            <resource-root path="postgresql-9.4.1210.jar"/>
        </resources>
        <dependencies>
            <module name="javax.api"/>
            <module name="javax.transaction.api"/>
        </dependencies>
    </module>

    [root@sayantan ~]# tree /usr/local/sayantan/EAP-7.0.0/modules/com/
    /usr/local/sayantan/EAP-7.0.0/modules/com/
    └── mysql
        └── main
            ├── module.xml
            └── mysql-connector-java-5.1.31.jar
    
    2 directories, 2 files
    
    [root@sayantan ~]# nano module.xml
    <?xml version="1.0" ?>
    <module xmlns="urn:jboss:module:1.1" name="com.mysql">
        <resources>
            <resource-root path="mysql-connector-java-5.1.31.jar"/>
        </resources>
        <dependencies>
            <module name="javax.api"/>
            <module name="javax.transaction.api"/>
        </dependencies> 
    </module>


apart from that, the project structure should itself have __*persistence.xml*__ and __*aggregator-ds.xml*__ files intact.
	
configure vnc
-------------

    sudo apt-get update && sudo apt-get upgrade
    sudo apt-get install ubuntu-desktop gnome-panel gnome-settings-daemon metacity nautilus gnome-terminal  xfce4 xfce4-goodies tightvncserver
    	
    ssh -L 5901:127.0.0.1:5901 user@example.com
    vncserver :1
    
    vncserver -kill :1
    
   nano ~/.vnc/xstartup
    #!/bin/sh
    
    # Uncomment the following two lines for normal desktop:
    # unset SESSION_MANAGER
    # exec /etc/X11/xinit/xinitrc
    
    [ -x /etc/vnc/xstartup ] && exec /etc/vnc/xstartup
    [ -r $HOME/.Xresources ] && xrdb $HOME/.Xresources
    xsetroot -solid grey 
    vncconfig -iconic &
    x-terminal-emulator -geometry 80x24+10+10 -ls -title "$VNCDESKTOP Desktop" &
    x-window-manager &
            
    gnome-panel &
    gnome-settings-daemon &
    metacity &
    nautilus &
    # END OF FILE xstartup
    
   Add the following to the bottom of the crontab -e.
    
    @reboot /usr/bin/vncserver :1
    
   nano /etc/init.d/vncserver
    
    #!/bin/bash
    PATH="$PATH:/usr/bin/"
    export USER="user"
    DISPLAY="1"
    DEPTH="16"
    GEOMETRY="1024x768"
    OPTIONS="-depth ${DEPTH} -geometry ${GEOMETRY} :${DISPLAY} -localhost"
    . /lib/lsb/init-functions
    
    case "$1" in
    start)
    log_action_begin_msg "Starting vncserver for user '${USER}' on localhost:${DISPLAY}"
    su ${USER} -c "/usr/bin/vncserver ${OPTIONS}"
    ;;
    stop)
    log_action_begin_msg "Stopping vncserver for user '${USER}' on localhost:${DISPLAY}"
    su ${USER} -c "/usr/bin/vncserver -kill :${DISPLAY}"
    ;;
    restart)
    $0 stop
    $0 start
    ;;
    esac
    exit 0
    
    # END OF FILE vncserver
    
   final touches
   
    sudo chmod +x /etc/init.d/vncserver
    sudo service vncserver start
    
    ssh -L 5901:xxx.xxx.xxx.xxx:5901 -N -f -l user server_ip_address
    
    sudo update-rc.d vncserver defaults
	
configure Testing
-----------------

	
configure HA
------------

	
configure Jenkins
-----------------


configure VNC
-------------
    
    root@ubuntu-2gb-lon1-01:~# apt-get install tightvncserver vnc4server
    root@ubuntu-2gb-lon1-01:~#     apt-get install xfce4 xfce4-goodies tightvncserver
                                   sudo apt-add-repository ppa:ubuntu-mate-dev/xenial-mate
                                   apt install mate mate-core mate-desktop-environment mate-desktop-environment-extras
                                   apt full-upgrade
    root@ubuntu-2gb-lon1-01:~# more ~/.vnc/xstartup
    #!/bin/sh
    export XKL_XMODMAP_DISABLE=1
    # Uncomment the following two lines for normal desktop:
    unset SESSION_MANAGER
    unset DBUS_SESSION_BUS_ADDRESS
    # exec /etc/X11/xinit/xinitrc
    
    # xrdb $HOME/.Xresources
    # startxfce4 &
    # mate-session &

    
    [ -x /etc/vnc/xstartup ] && exec /etc/vnc/xstartup
    [ -r $HOME/.Xresources ] && xrdb $HOME/.Xresources
    xsetroot -solid grey 
    vncconfig -iconic &
    /usr/bin/autocutsel -s CLIPBOARD -fork
    gnome-session --session=gnome-fallback --disable-acceleration-check &
    x-terminal-emulator -geometry 80x24+10+10 -ls -title "$VNCDESKTOP Desktop" &
    x-window-manager &
    
    unity-panel-service &
    unity-settings-daemon &
    gnome-settings-daemon &
    metacity &
    nautilus &
    gnome-panel &
    gnome-terminal &
    
    root@ubuntu-2gb-lon1-01:~# more /etc/init.d/vncserver 
    #!/bin/bash
    PATH="$PATH:/usr/bin/"
    export USER="root"
    DISPLAY="1"
    DEPTH="32"
    GEOMETRY="1024x768"
    OPTIONS="-depth ${DEPTH} -geometry ${GEOMETRY} :${DISPLAY} -localhost"
    . /lib/lsb/init-functions
    
    case "$1" in
    start)
    log_action_begin_msg "Starting vncserver for user '${USER}' on localhost:${DISPLAY}"
    su ${USER} -c "/usr/bin/vncserver ${OPTIONS}"
    ;;
    stop)
    log_action_begin_msg "Stopping vncserver for user '${USER}' on localhost:${DISPLAY}"
    su ${USER} -c "/usr/bin/vncserver -kill :${DISPLAY}"
    ;;
    restart)
    $0 stop
    $0 start
    ;;
    esac
    exit 0
    
    root@ubuntu-2gb-lon1-01:~# vi /etc/vncserver/vncservers.conf
    VNCSERVERS="1:root 3:priyanka 4:srijita 5:sayantan"
    VNCSERVERARGS[1]="-geometry 1024x768"
    VNCSERVERARGS[3]="-geometry 1024x768"
    VNCSERVERARGS[4]="-geometry 1024x768"
    VNCSERVERARGS[5]="-geometry 1024x768"
    
    root@ubuntu-2gb-lon1-01:~# 
    chmod +x ~/.vnc/xstartup
    vncserver -kill :1
    vncserver :1

