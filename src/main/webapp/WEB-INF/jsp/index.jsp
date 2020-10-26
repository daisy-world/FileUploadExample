<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <title> index</title>
</head>
<body  style="margin-left:400px;margin-top:100px;">
<div class="container col-lg-12" >

     <div class="col-lg-12">
     <form action = "uploadFile" method = "post" enctype="multipart/form-data">
     
     	<h4>Upload File</h4>
            <div class="form-group form-group-lg">
                <label class="col-sm-4 control-label">Select a file:</label>
                <div class="col-sm-4">
                    <input type="file" class="form-control" id="file" name="file"/><br/>
                    <button type="submit" class = "button btn-success">Upload</button>
                    
                </div>
            </div>

</form>
</div>
</div>
</body>

</html>

