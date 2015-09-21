<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../commons/mcommons.jsp" %>
<script src="${staUrl }/js/f.datagrid.js"></script>
</head>
<body>
	<div id="hello" class="container"></div>
	<div class="container" data-example-id="bordered-table">
    <table class="table table-bordered">
      <thead>
        <tr>
          <th>#</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Username</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row">1</th>
          <td>Mark</td>
          <td>Otto</td>
          <td>@mdo</td>
        </tr>
        <tr>
          <th scope="row">2</th>
          <td>Jacob</td>
          <td>Thornton</td>
          <td>@fat</td>
        </tr>
        <tr>
          <th scope="row">3</th>
          <td>Larry</td>
          <td>the Bird</td>
          <td>@twitter</td>
        </tr>
      </tbody>
      <tfoot class="form-inline"><p>helloworld</p></tfoot>
    </table>
  </div>
<script type="text/javascript">
$(function(){
	$("#hello").f_dg({
		url:"http://img.365020.com/test.js",
		columns:[{
			name:"hello1",
			field:"type"
		},{
			name:"hello2",
			field:"type"
		},{
			name:"hello3",
			field:"type"
		},{
			name:"hello4",
			field:"type"
		}],
		filter:function(data){
			console.log(data);
			return data;
		},
		pagination:true
	});
})
</script>
</body>
</html>