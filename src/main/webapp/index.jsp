<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Online Java Executor</title>
    <script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>
    <script type="text/javascript" src="scripts/jquery.json-2.2.js"></script>
</head>
<body>

<div>
    <p>Expression: <textarea name="exp" id="exp" cols="50" rows="2"></textarea></p>
    <p style="color: gray;">Ex: 'ABC'.concat('DEF').toLowerCase()</p>
    <p><input type="button" name="run" id="run" value="Run" /></p>
</div>

<div id="result"></div>
<script type="text/javascript">

    var url = 'exec';

    $(document).ready(function() {
        $("#run").click( function() {
            var expression = $('#exp').val();
            $.getJSON(url, { exp: expression },
                    function(data) {
                        $.each(data, function(key, val) {
                            $("#result").html("Result: " + val);
                        });
                    });
        } );
    });

</script>

</body>
</html>