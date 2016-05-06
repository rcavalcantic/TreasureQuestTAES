<?php
  $id_time =$_GET['id_time'];
  
  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('treasurequest',$conexao);
  
  $sql="select * from jogadores where id_time=".$id_time;
  
  $resultado = mysql_query($sql) or die ("Error :" . mysql_error());

  if (mysql_num_rows($resultado) > 0)
      echo "true";
  else
      echo "false";
?>
	
