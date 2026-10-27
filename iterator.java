static void func(ArrayList mylist) {
    Iterator it = mylist.iterator();

    while (it.hasNext()) {
        Object element = it.next();

        if (element.equals("###")) {
            break;
        }
    }

    while (it.hasNext()) {
        Object element = it.next();
        System.out.println((String) element);
    }
}