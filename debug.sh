#!/bin/bash

echo "Select a class to debug:"
echo "1) ListDemo"
echo "2) Demo"

read -p "Enter the number: " choice

case $choice in
  1)
    class="ListDemo"
    ;;
  2)
    class="Demo"
    ;;

  *)
    echo "Invalid option."
    exit 1
    ;;
esac

rlwrap -c -D 2 -r jdb -classpath build -sourcepath . $class
 
 
