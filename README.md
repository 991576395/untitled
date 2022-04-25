将要匹配的字符串拆词匹配目标字符串，并记录匹配上的位置


demo:
  SplitStrinToMatch splitStrinToMatch = new SplitStrinToMatch();
  System.out.println(new Gson().toJson(splitStrinToMatch.checkStringCode("你好哟","阿斯蒂芬你阿斯蒂芬好阿斯蒂芬你好")));
  
  运行结果
  {"你":[4],"你好":[14],"好":[9]}
