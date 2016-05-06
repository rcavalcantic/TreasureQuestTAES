<?php
  $nome =$_GET['nome'];
  $id_time =$_GET['id_time'];
  $cor = $_GET['cor'];
  
  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('treasurequest',$conexao);
  
   $sql="select * from jogadores where nome like '".$nome."' and id_time=".$id_time." and cor=".$cor;
  
  $resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
  if (mysql_num_rows($resultado) > 0)
      echo "true";
  else
      echo "false";
?>
