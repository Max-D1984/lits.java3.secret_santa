package service;

import dal.UserTargetDal;
import dal.UserTargetDalImpl;
import pojo.User;
import pojo.UserTarget;

import java.util.*;

public class UserTargetServiceImpl implements UserTargetService {
    private UserTargetDal userTargetDal = new UserTargetDalImpl();

    @Override
    public void update(int id, UserTarget userTarget) {
        userTargetDal.update(id, userTarget);
    }

    @Override
    public void delete(int id) {
        userTargetDal.delete(id);
    }

    @Override
    public void create(UserTarget userTarget) {
        userTargetDal.create(userTarget);
    }

    @Override
    public UserTarget read(int id) {
        return userTargetDal.read(id);
    }

    @Override
    public List<UserTarget> readList() {
        return userTargetDal.readList();
    }

    @Override
    public void createList(List<User> userList) {
        userTargetDal.createList(generateMapOfUsers(userList));
    }

    @Override
   /*
   Метод для генерації Таємного Санти для всіх користувачів в групі.
   На вході - список Юзерів (колекція, яка відповідає за людей, котрі мають щось дарувати іншим),
   на виході методу - колекція типу Map в якій елементи зберігаються як пара "ключ - значення".
   У нас "ключ" -> id юзера, а "значення" -> id таргета.
    */
    public Map<Integer, Integer> generateMapOfUsers(List<User> userList) {
        //Мапа в якій будемо зберігати пари "id хто дарує - id кому дарує"
        Map<Integer, Integer> mapOfSecretSanta = new HashMap<>();
        //Створюємо допоміжну колекцію, яка
        List<User> targetList = new LinkedList<>();
        //Об'єкт класу Random, який ми використаємо для генерації випадкових чисел
        Random random = new Random();
        //Змінна, яка є ідентифікатором об'єктів в списку targetList
        int target;
        //Клонуємо всі об'єкти з вхідного списку userList в створений список targetList
        targetList.addAll(userList);
        //Головний цикл, в якому відбуваються всі процеси генерації пар "id Санти - id цілі"
        for (User user : userList) {
            System.out.println();
            //номер ітерації
            System.out.println("Iteration number: " + (user.getId()));
            //Виводить список "цілей" ДО виконання певної логіки
            System.out.println("Target list before: " + targetList);
            //Виводить id Санти
            System.out.println("Santa id: " + user.getId());
        /*
        Присвоюємо змінній target навмання згенероване число від 0 (включно) до числа, яке дорівнює розмірові списка
        targetList (не включно). Якщо в компанії 8 людей, тоді у нас згенерується цифра від 0 до 7.
         */
            target = random.nextInt(targetList.size());
            //Виводим id для "цілі"
            System.out.println("Target id: " + targetList.get(target).getId());
        /*
        Логіка для передостанньої ітерації: якщо кількісь об'єктік в списку targetList рівна 2 і
        в списку targetList є об'єкт, який розташований на останньому місці в списку userList, тоді
        заходим в цей блок коду.
         */
            if (targetList.size() == 2 && targetList.contains(userList.get(userList.size() - 1))) {
            /*
            Цей блок коду потрібен для запобігання випадку, коли на останній інтерації для останнього юзера
            на вибір "цілі" залишається він же сам собі. Тобто випадок коли виникає пара userId=targetId.
            В цьому випадку на передостанній ітерації ми добровільно-примусово передостанньому об'єктові
            зі списку userList надаєм в пару останній об`єкт зі списку targetList.
             */
                System.out.println("Target list size equals 2 and contain last element from user list");
                //Виводим замінений об'єкт
                System.out.println("Auto change Target id: " + targetList.get(targetList.indexOf(userList.get(userList.size() - 1))).getId());
                //Заносим в нашу Мапу значення id Санти і id "цілі"
                mapOfSecretSanta.put(user.getId(), userList.get(userList.size() - 1).getId());
                //Видаляємо зі списку "цілей" (targetList) об'єкт, для якого вже є пара зі списку userList
                targetList.remove(targetList.get(targetList.indexOf(userList.get(userList.size() - 1))));
                //Виводим на екран мапу
                System.out.println(mapOfSecretSanta);
                //Виводим змінений список "цілей" після видалення одного об'єкта
                System.out.println("Changed target list: " + targetList);
                //Далі блок коду, який не відповідає вище вказаній ситуації.
            } else {
            /*
            Даний блок коду описує ситуацію, коли id Санти співпадає з id "цілі" в не залежності від того, яка
            зараз ітерація
             */
                if (user.getId() == targetList.get(target).getId()) {
                    //Виводим список "цілей"
                    System.out.println("Target list: " + targetList);
                /*
                Видалаємо об'єкт зі списку "цілей", який нам рандомно згенерувався.
                    P.S.: якщо ми зайшли в даний блок коду (читайте умову входження в даний блок), то по суті
                    згенерований об'єкт відповідає ситуації userId=targetId.
                 */
                    targetList.remove(target);
                    //Виводим новий тимчасовий список "цілей"
                    System.out.println("New target list: " + targetList);
                    //Повторяєм процес рандомної генерації об'єкта зі списку "цілей"
                    target = random.nextInt(targetList.size());
                    //Виводим новий об'єкт зі списку "цілей"
                    System.out.println("New Target id: " + targetList.get(target).getId());
                    //Заносим в мапу відповідну пару id Санти - id "цілі"
                    mapOfSecretSanta.put(user.getId(), userList.get(userList.indexOf(targetList.get(target))).getId());
                    //Видаляємо зі списку "цілей" елемент, який вже знайшов собі пару
                    targetList.remove(target);
                    //Повертаємо назад в список "цілей" об'єкт, видалений на початку блоку
                    targetList.add(user);
                    //Виводимо змінений список "цілей"
                    System.out.println("Changed target list: " + targetList);
                /*
                Блок коду, в якому не передбачені всі вище перелічені випадки. Нормальний перебіг генерування
                пар id Санти - id "цілі"
                 */
                } else {
                    //Закидаємо в мапу пари id Санти - id "цілі"
                    mapOfSecretSanta.put(user.getId(), userList.get(userList.indexOf(targetList.get(target))).getId());
                    //Видаляємо зі списку "цілей" елемент, який вже знайшов собі пару
                    targetList.remove(target);
                }
                //Виводимо вміст мапи
                System.out.println(mapOfSecretSanta);
                //Виводимо список "цілей" в кінці ітерації, після виконання первної логіки
                System.out.println("Target list after: " + targetList);
            }
        }
        return mapOfSecretSanta;
    }
}
