#! /bin/bash
#
# Builds and installs the RestExpress Kickstart application into ~/local/RestExpress
# to be used by the restexpress.sh script to create new applications with your
# own name.
#
# Assumes an 'ant release' has already been performed and that there
# are artifacts in the dist/ directory.
#
if [ -z "$1" ]; then
	RESTEXPRESS_HOME=~/local/RestExpress
fi

if [ -e "$RESTEXPRESS_HOME" ]; then
	rm -rf $RESTEXPRESS_HOME
fi

unzip dist/RestExpress-*-kickstart.zip -d /tmp/restexpress
mv /tmp/restexpress/kickstart $RESTEXPRESS_HOME
rm -rf /tmp/restexpress
