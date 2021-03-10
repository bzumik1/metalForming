#!/bin/bash
branch=master


# prints colored text
print_with_color () {
    COLOR="92m"; # sets color of the text
    START_COLOR="\e[$COLOR";
    END_COLOR="\e[0m";

    printf "$START_COLOR%b$END_COLOR" "$1";
}


#clear old data
print_with_color "CLEARING OLD DATA ...\n";
rm -rf ./tmp #removes tmp folder if already exists
rm -rf ./src/main/resources/static #removes old static content if exists

#clone FE
print_with_color "CLONING ...\n"
mkdir "tmp"
git clone git@code.siemens.com:apcprague/edge/metal-forming-fe.git tmp
git checkout $branch

#build FE
print_with_color "BUILDING ...\n"
pushd ./tmp
npm install
ng build --prod
popd


print_with_color "COPYING ...\n"
cp -af ./tmp/dist/metal-forming/. ./src/main/resources/static
print_with_color "REMOVING TEMPORARY FILES ...\n"
rm -rf tmp
print_with_color "DONE!\n"
