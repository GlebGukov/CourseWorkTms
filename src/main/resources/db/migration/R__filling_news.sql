insert into news (id, anons, archived, full_text, title, views, type_of_news)
values (gen_random_uuid(), 'World Test FLYWAY anons', false, 'World Test FLYWAY full_text',
        'World Test FLYWAY title', 111, 'World');

insert into news (id, anons, archived, full_text, title, views, type_of_news)
values (gen_random_uuid(), 'Technology Test FLYWAY anons', false, 'Technology Test FLYWAY full_text',
        'Technology Test FLYWAY title',
        222, 'Technology');

insert into news (id, anons, archived, full_text, title, views, type_of_news)
values (gen_random_uuid(), 'Design Test FLYWAY anons', false, 'Design Test FLYWAY full_text',
        'Design Test FLYWAY title',
        333, 'Design');

insert into news (id, anons, archived, full_text, title, views, type_of_news)
values (gen_random_uuid(), 'Culture Test FLYWAY anons', false, 'Culture Test FLYWAY full_text',
        'Culture Test FLYWAY title',
        444, 'Culture');

insert into news (id, anons, archived, full_text, title, views, type_of_news)
values (gen_random_uuid(), 'Business Test FLYWAY anons', true, 'Business Test FLYWAY full_text',
        'Business Test FLYWAY title',
        555, 'Business');

insert into news (id, anons, archived, full_text, title, views, type_of_news)
values (gen_random_uuid(), 'Politics Test FLYWAY anons', true, 'Politics Test FLYWAY full_text',
        'Politics Test FLYWAY title',
        666, 'Politics');
insert into news (id, anons, archived, full_text, title, views, type_of_news, approved)
values (gen_random_uuid(),
        'В Германии противники ядерной энергетики отмечают грядущее закрытие последних атомных энергоблоков. Оно запланировано на субботу.',
        true, 'Активисты складывают тысячи бумажных журавликов — как символ безопасности и мира.
Правда, по их словам, победа не окончательная: ещё многие годы будет решаться вопрос об утилизации атомных отходов и оборудования реакторов.
Мартина Гремлер, пресс-секретарь BUND:
«Мы считаем, что использовать технологию, связанные с которой риски мы не можем вполне контролировать, совершенно неправильно, учитывая, что у нас нет даже временных, не говоря уже о постоянных, хранилищ».
О возможности отказа от АЭС в Берлине говорили ещё с начала века. Катастрофа в Фукусиме стала последней каплей, и именно после неё приняли решение остановить все немецкие реакторы.',
        'Немецкие экоактивисты отмечают закрытие АЭС', 123,
        'World', true)


