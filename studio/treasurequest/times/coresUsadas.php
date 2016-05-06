<?php
  $nomeTime =$_GET['nomeTime'];
  
  $conexao = mysql_connect('localhost','root','');
  
  mysql_select_db('tesouro',$conexao);
  
  $sql = 'SELECT jogadores.cor 
  		FROM times
  		INNER join jogadores
  		ON jogadores.id_time LIKE times.id_time   		
   			
   			WHERE times.nome LIKE '.$nomeTime;
    
  $resultado = mysql_query($sql) or die ("Error: " . mysql_error());
  
  while($linha = mysql_fetch_object($resultado))
      echo $linha->cor.";";
?>