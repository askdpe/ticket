<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>排座模块</title>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/reset.css" />
<style type="text/css">
.front{width: 300px;margin: 5px 32px 45px 32px;background-color: #f0f0f0;	color: #666;text-align: center;padding: 3px;border-radius: 5px;}
.booking_area {float: right;position: relative;width:200px;height: 450px; }
.booking_area h3 {margin: 5px 5px 0 0;font-size: 16px;}
.booking_area p{line-height:26px; font-size:16px; color:#999}
.booking_area p span{color:#666}
div.seatCharts-cell {color: #182C4E;height: 25px;width: 25px;line-height: 25px;margin: 3px;float: left;text-align: center;outline: none;font-size: 13px;}
div.seatCharts-seat {color: #fff;cursor: pointer;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;}
div.seatCharts-row {height: 35px;}
div.seatCharts-seat.available {background-color: #B9DEA0;}
div.seatCharts-seat.focused {background-color: #76B474;border: none;}
div.seatCharts-seat.selected {background-color: #E6CAC4;}
div.seatCharts-seat.unavailable {background-color: #472B34;cursor: not-allowed;}
div.seatCharts-container {border-right: 1px dotted #adadad;width: 400px;padding: 20px;float: left;}
div.seatCharts-legend {padding-left: 0px;position: absolute;bottom: 16px;}
ul.seatCharts-legendList {padding-left: 0px;}
.seatCharts-legendItem{float:left; width:90px;margin-top: 10px;line-height: 2;}
span.seatCharts-legendDescription {margin-left: 5px;line-height: 30px;}
.checkout-button {display: block;width:80px; height:24px; line-height:20px;margin: 10px auto;border:1px solid #999;font-size: 14px; cursor:pointer}
#seats_chose {max-height: 150px;overflow-y: auto;overflow-x: none;width: 200px;}
#seats_chose li{float:left; width:72px; height:26px; line-height:26px; border:1px solid #d3d3d3; background:#f7f7f7; margin:6px; font-size:14px; font-weight:bold; text-align:center}
</style>
</head>
<body>
<div class="demo clearfix">
	<div id="seat_area">
		<div class="front">屏幕</div>					
	</div>
	<div class="booking_area">
		<p>影院：<span><%=request.getAttribute("place") %></span></p>
		<p>厅号：<span><%=request.getAttribute("name") %></span></p>
		<p>删除座位：</p>
		<ul id="seats_chose"></ul>
		<p>删除座位数：<span id="tickects_num">0</span></p>
		<input type="button" class="btn"  onclick="send()" value="提交排座表"/>
		<div id="legend"></div>
	</div>
</div>

<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.seat-charts.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/layer/2.4/layer.js"></script>
<script type="text/javascript">
var price =${requestScope.price}; //电影票价
var newmap=${requestScope.newmap};
var row=${requestScope.row};
var pai=${requestScope.pai};
var arrs=new Array();
function send(){
    layer.confirm('确认要提交吗？', function() {
        $.ajax({
        	url:"sendnew.action",
               data:{"arr":JSON.stringify(arrs),
            	"row":row,
            	"pai":pai
               },
        	   type:"post",
              dataType:"json",
            success : function(result) {

                if (result == 0) {
                    layer.msg('提交失败', {
                        icon : 1,
                        time : 1000
                    });
                } else {
                	layer.msg('提交成功', {
                        icon : 1,
                        time : 1000
                    });

                }
            },
            error : function() {
                layer.msg('请求服务器失败', {
                    icon : 2,
                    time : 1000
                });
            }
        });
    });
}
$(document).ready(function() {
	var $cart = $('#seats_chose'), //座位区
			$tickects_num = $('#tickects_num'), //票数
			$total_price = $('#total_price'); //票价总额

	var sc = $('#seat_area').seatCharts({
		map:newmap,
		naming: {//设置行列等信息
			top: false, //不显示顶部横坐标（行） 
			getLabel: function(character, row, column) { //返回座位信息 
				return column;
			}
		},
		legend: {//定义图例
			node: $('#legend'),
			items: [
				['c', 'available', '保留座位'],
				['c', 'selected', '删除座位']
			]
		},
		click: function() {
			if (this.status() == 'available') { //若为可选座状态，添加座位
				$('<li>' + (this.settings.row + 1) + '排' + this.settings.label + '座</li>')
						.attr('id', 'cart-item-' + this.settings.id)
						.data('seatId', this.settings.id)
						.appendTo($cart);

				$tickects_num.text(sc.find('selected').length + 1); //统计选票数量
				$total_price.text(getTotalPrice(sc) + price);//计算票价总金额
                arrs.push((this.settings.row + 1)+'_'+(this.settings.label));
				return 'selected';
			} else if (this.status() == 'selected') { //若为选中状态

				$tickects_num.text(sc.find('selected').length - 1);//更新票数量
				$total_price.text(getTotalPrice(sc) - price);//更新票价总金额
				$('#cart-item-' + this.settings.id).remove();//删除已预订座位
				alert("del");
				removeByValue(arrs, (this.settings.row + 1)+'_'+(this.settings.label));
				return 'available';
			} else if (this.status() == 'unavailable') { //若为已售出状态
				return 'unavailable';
			} else {
				return this.style();
			}
		}
	});
	//设置已售出的座位
	function removeByValue(arr, val) {
		  for(var i=0; i<arr.length; i++) {
		    if(arr[i] == val) {
		      arr.splice(i, 1);
		      break;
		    }
		  }
	}
});

function getTotalPrice(sc) { //计算票价总额
	var total = 0;
	sc.find('selected').each(function() {
		total += price;
	});
	return total;
}

</script>

</body>
</html>