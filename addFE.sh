#!/bin/bash
branch=master

mkdir "tmp"
git clone git@code.siemens.com:apcprague/edge/metal-forming-fe.git tmp
git checkout $branch

pushd ./tmp
npm install
ng build
popd


cp -af ./tmp/dist/metal-forming/. ./src/main/resources/static

rm -rfv tmp
echo "DONE"
