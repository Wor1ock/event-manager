# О проекте
_«Афиша»_ - сервис управления событиями. Пользователи могут создавать мероприятия, приглашать гостей, устанавливать расписание, отслеживать участников и их статусы.

**Бизнес-цель приложения:**
Обеспечить пользователям возможность эффективно организовывать, управлять и отслеживать мероприятия, приглашать гостей, устанавливать расписание и следить за участниками и их статусами.

# Подход к разработке
**Feature branch workflow** — простой и самый популярный вариант подходов работы. Если коротко, в нём для каждого нового изменения создаётся новая ветка, которая позже вливается в `main` с помощью `git merge`.

**Основные правила:**
- новая функциональность или исправление — новая ветка;
- когда код в `feature`-ветке готов, он вливается в `main`;
- в `main` всегда рабочая версия без «недоделок».

**Если аккуратно следовать подходу feature branch workflow, то:**
- Не будет проблемы «расхождения» веток, ведь новые изменения попадают в `main` через `git merge`, а не через `git push`. Команду `merge` «разошедшиеся» ветки не смущают, ведь для них она и придумана.
- В ветке `main` всегда рабочая версия проекта. Все «полуфабрикаты» и недоделанные функциональности находятся в `feature`-ветках, пока не будут готовы попасть в `main`.
# Правила названия веток:
*Ветка* - изолированный поток разработки проекта. Ветки нужно называть так, чтобы другим участникам было понятно, что в них происходит.

Мы будем использовать указатели `feature` для веток, где прорабатывается новая функциональность, и `fix`для веток, где ведётся работа по исправлению ошибок.

После ключевого слова идёт слеш и описание проблемы или задачи (например, `/add-branch-info`). Это описание не должно содержать пробелов — следует использовать нижнее подчёркивание или дефис.

**Примеры:**
`feature/add_branch_info`
`fix/main_button`

# Правила комментариев к коммитам:
Есть общие рекомендации по тому, как правильно составить сообщение. Оно должно быть:
- относительно коротким, чтобы его было легко прочитать;
- информативным.

Хороший коммит | Плохой коммит
---| --
Исправление опечатки в заголовке главной страницы на хорватском | Исправлена опечатка

**Основные правила:**
- длина сообщения от 30 до 72 символов;
- формат коммита: `<type>: <сообщение>`.
- первое слово — глагол в инфинитиве («исправить», «дополнить», «добавить» и другие);

Первая часть `type` — это тип изменений. 
Например: 
- `feat` — для новой функциональности;
- `fix` — для исправленных ошибок.

**Пример:**
`git commit -m "feat: добавить подсчёт суммы заказов за неделю"`