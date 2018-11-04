<html>
<body>
<h2>Hello World!</h2>
    <script>
        var i = new Number(17);
        i.toTest = function() {
            alert("nishao");
        }

        //i.toTest();
        //alert(i);
        i = 18;
        //i.toTest();

        var n = 17;
        bString = n.toString(2);
        //alert(bString);

        //alert(n.toFixed(2))
        console.info(parseInt("22jo!!!"))
        console.info(parseInt("77", 8));

        var i = 0;
        var i = "abc";

        console.info(i);

        if (new Boolean(false)) {
            console.info("it is true")
        }


        var global = this;

        console.info(this);
        console.info("this")
        function test() {
            abc = "jjj";
            //console.info(j);
            console.info(i);
            if (-0 == 0) {
                console.info("-0 == 0")
                var j = 111111;
                var i = 111;
                console.info(this);
            }
            console.info(j);
            console.info(global.parseInt("444"));
        }

        var abcd = ["4", "9", "666", "89"];
        abcd.sort();
        console.info(abcd);
        console.info(typeof NaN);
        //console.info(abc);
        test();

        var m = 0234;
        console.info(m + 1 + "88\9")
        console.info("\xA9");


        var zk1 = "abc";
        zk2 = "mm";
        console.info(zk1);
        console.info(zk2);
        delete  zk2;
        delete zk1;
        console.info("jjjojojio");
        console.info(zk1);
        //console.info(zk2);

        var ii = "j";
        var array1 = [1, ii + 1, , 6,];
        console.info(array1[1]);
        ii = 4;
        console.info(array1[1]);
        console.info(array1.length);

        var obj = {
            j1 : 4,
            ma : 6
        }

        alert("j1" in obj);

        var hh = 6/4;
        console.info(hh);


        if(0 === -0)
            alert("null == null")

        if (null === undefined)
            alert("null == undefined");


        var foo = function() {
            var x = 1;
            eval("x = 2");
            eval("var yyy = 3");
            console.info(x);
            console.info(yyy);
        }
        foo();


    </script>
</body>
</html>
