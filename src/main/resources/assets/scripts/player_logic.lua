function initialize(entity)
end

function onAdd(entity)
end

function onRemove(entity)
end

function update(entity, deltaTime)
    System.out:println(entity.transform.position)
    if Gdx.input:isKeyPressed(Keys.LEFT) then
        entity.transform:move(-10, 0)
    elseif Gdx.input:isKeyPressed(Keys.RIGHT) then
        entity.transform:move(10, 0)
    end

    if Gdx.input:isKeyPressed(Keys.UP) then
        entity.transform:move(0, 10)
    elseif Gdx.input:isKeyPressed(Keys.DOWN) then
        entity.transform:move(0, -10)
    end
end